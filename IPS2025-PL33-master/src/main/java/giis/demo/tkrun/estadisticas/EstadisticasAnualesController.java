package giis.demo.tkrun.estadisticas;

import giis.demo.tkrun.employee.EmployeeModel;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.estadisticas.EstadisticasAnualesView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.*;

public class EstadisticasAnualesController {

    private IngresoDAO ingresoDAO;
    private GastoDAO gastoDAO;
    private EmployeeModel employeeModel;
    private EstadisticasAnualesView view;
    private AuditService audit;

    public EstadisticasAnualesController(EstadisticasAnualesView view, AuditService audit) {
        this.view = view;
        this.setAudit(audit);
        this.ingresoDAO = new IngresoDAO();
        this.gastoDAO = new GastoDAO();
        this.employeeModel = new EmployeeModel();

        addListeners();
        view.setVisible(true);
    }

    private void addListeners() {
        view.getBtnGenerar().addActionListener(e -> generarEstadisticas());
    }

    private void generarEstadisticas() {
        int año;
        try {
            año = Integer.parseInt(view.getAno());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Año no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // -------------------------
        // Validar que no sea un año futuro
        // -------------------------
        int añoActual = Calendar.getInstance().get(Calendar.YEAR);
        if (año > añoActual) {
            JOptionPane.showMessageDialog(view,
                "No hay estadísticas para años futuros.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Fechas inicio y fin del año
        Calendar cal = Calendar.getInstance();
        cal.set(año, Calendar.JANUARY, 1, 0, 0, 0);
        Date inicio = cal.getTime();
        cal.set(año, Calendar.DECEMBER, 31, 23, 59, 59);
        Date fin = cal.getTime();

        // -------------------------
        // 1. Obtener ingresos
        // -------------------------
        List<Ingreso> ingresos = ingresoDAO.getIngresosBetween(inicio, fin);
        Map<Integer, Double> totalesIngresosPorMes = new HashMap<>();
        for (Ingreso i : ingresos) {
            cal.setTime(i.getFechaHora());
            int mes = cal.get(Calendar.MONTH) + 1;
            totalesIngresosPorMes.put(mes, totalesIngresosPorMes.getOrDefault(mes, 0.0) + i.getTotal());
        }

        // -------------------------
        // 2. Obtener gastos de Restock
        // -------------------------
        List<Gasto> gastos = gastoDAO.getGastosByYear(año);
        Map<Integer, Double> totalesGastosPorMes = new HashMap<>();
        for (Gasto g : gastos) {
            cal.setTime(g.getFechaHora());
            int mes = cal.get(Calendar.MONTH) + 1;
            totalesGastosPorMes.put(mes, totalesGastosPorMes.getOrDefault(mes, 0.0) + g.getTotal());
        }

        // -------------------------
        // 3. Añadir sueldos prorrateados por mes
        // -------------------------
        List<EmployeeViewDTO> empleados = employeeModel.getEmployees();
        for (int mes = 1; mes <= 12; mes++) {
            double totalSueldos = 0;
            for (EmployeeViewDTO emp : empleados) {
                totalSueldos += emp.salario_anual / 12.0;
            }
            totalesGastosPorMes.put(mes, totalesGastosPorMes.getOrDefault(mes, 0.0) + totalSueldos);
        }

        // -------------------------
        // 4. Crear dataset para gráfico
        // -------------------------
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int mes = 1; mes <= 12; mes++) {
            double ingresosMes = totalesIngresosPorMes.getOrDefault(mes, 0.0);
            double gastosMes = totalesGastosPorMes.getOrDefault(mes, 0.0);
            dataset.addValue(ingresosMes, "Ingresos", getNombreMes(mes));
            dataset.addValue(gastosMes, "Gastos", getNombreMes(mes));
        }

        // -------------------------
        // 5. Crear gráfico
        // -------------------------
        JFreeChart chart = ChartFactory.createBarChart(
            "Ingresos y Gastos del año " + año,
            "Mes",
            "Cantidad (€)",
            dataset
        );

        view.getPanelGrafico().removeAll();
        view.getPanelGrafico().add(new ChartPanel(chart), BorderLayout.CENTER);
        view.getPanelGrafico().revalidate();
        view.getPanelGrafico().repaint();

     // -------------------------
     // 6. Calcular balance total anual
     // -------------------------
     double totalIngresos = totalesIngresosPorMes.values().stream().mapToDouble(Double::doubleValue).sum();
     double totalGastos = totalesGastosPorMes.values().stream().mapToDouble(Double::doubleValue).sum();
     double balance = totalIngresos - totalGastos;

     // Determinar si es positivo o negativo
     String estadoBalance = (balance >= 0) ? "Positivo" : "Negativo";

     // Mostrar balance en la vista
     view.setBalanceText(String.format(
             "Balance: %.2f € (%s) → Ingresos: %.2f €, Gastos: %.2f €",
             balance, estadoBalance, totalIngresos, totalGastos));
    }


    private String getNombreMes(int mes) {
        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio",
                          "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        return meses[mes - 1];
    }

	public AuditService getAudit() {
		return audit;
	}

	public void setAudit(AuditService audit) {
		this.audit = audit;
	}
}

package giis.demo.tkrun.estadisticas;

import giis.demo.tkrun.employee.EmployeeModel;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.estadisticas.EstadisticasMensualesView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.util.*;

public class EstadisticasMensualesController {

    private final EstadisticasMensualesView view;
    private final IngresoDAO ingresoDAO;
    private final EmployeeModel employeeModel;
    private final GastoDAO gastoDAO;

    public EstadisticasMensualesController(EstadisticasMensualesView view, AuditService audit) {
        this.view = view;
        this.ingresoDAO = new IngresoDAO();
        this.employeeModel = new EmployeeModel();
        this.gastoDAO = new GastoDAO();
        addListeners();
        view.setVisible(true);
    }

    private void addListeners() {
        view.getBtnGenerar().addActionListener(e -> generarEstadisticas());
    }

    private void generarEstadisticas() {
        // -------------------------
        // Ajustar fechas para cubrir todo el día
        // -------------------------
        Calendar cal = Calendar.getInstance();

        // inicio: 00:00:00 del día seleccionado
        cal.setTime(view.getFechaInicio());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date inicio = cal.getTime();

        // fin: 23:59:59 del mismo día
        cal.setTime(view.getFechaFin());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date fin = cal.getTime();

        int año = cal.get(Calendar.YEAR);
        int mesSeleccionado = cal.get(Calendar.MONTH) + 1;

        // -------------------------
        // 1. Ingresos del mes agrupados por tipo
        // -------------------------
        List<Ingreso> ingresos = ingresoDAO.getIngresosBetween(inicio, fin);
        Map<String, Double> ingresosPorTipo = new HashMap<>();
        for (Ingreso i : ingresos) {
            String tipo = i.getTipo().toString();
            cal.setTime(i.getFechaHora());
            int mes = cal.get(Calendar.MONTH) + 1;
            if (mes == mesSeleccionado) {
                ingresosPorTipo.merge(tipo, i.getTotal(), Double::sum);
            }
        }

        // -------------------------
        // 2. Gastos del mes: Restock + Sueldos
        // -------------------------
        List<EmployeeViewDTO> empleados = employeeModel.getEmployees();
        List<Gasto> gastos = gastoDAO.getGastosByYear(año);

        double totalSueldos = empleados.stream().mapToDouble(emp -> emp.salario_anual / 12.0).sum();

        double totalRestock = gastos.stream()
                .filter(g -> "Restock".equals(g.getTipo()))
                .filter(g -> {
                    cal.setTime(g.getFechaHora());
                    return cal.get(Calendar.MONTH) + 1 == mesSeleccionado;
                })
                .mapToDouble(Gasto::getTotal)
                .sum();



        // -------------------------
        // 3. Crear dataset para gráfico
        // -------------------------
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ingresosPorTipo.forEach((tipo, total) -> dataset.addValue(total, "Ingresos", tipo));
        dataset.addValue(totalSueldos, "Gastos", "Sueldos");
        dataset.addValue(totalRestock, "Gastos", "Restock");

        // -------------------------
        // 4. Crear gráfico
        // -------------------------
        JFreeChart chart = ChartFactory.createBarChart(
                "Ingresos y Gastos del mes " + mesSeleccionado + " de " + año,
                "Tipo",
                "Cantidad (€)",
                dataset
        );

        view.getPanelGrafico().removeAll();
        view.getPanelGrafico().add(new ChartPanel(chart), BorderLayout.CENTER);
        view.getPanelGrafico().revalidate();
        view.getPanelGrafico().repaint();

        // -------------------------
        // 5. Balance total del mes
        // -------------------------
        double totalIngresos = ingresosPorTipo.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalGastos = totalSueldos + totalRestock;

        view.setBalanceText(String.format(
                "Balance: %.2f € → Ingresos: %.2f €, Gastos: %.2f € (Sueldos: %.2f €, Restock: %.2f €)",
                totalIngresos, totalIngresos, totalGastos, totalSueldos, totalRestock));
    }
}

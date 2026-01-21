package giis.demo.tkrun.factura;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Color;

public class FacturaPDFGenerator {
	private static int contadorFactura = 1;


	public String generarFacturaPDF(FacturaDatos cliente, List<FacturaItem> items) {

		String nombreArchivo = "Factura_RealOviedo.pdf";

		try {
			Document document = new Document(PageSize.A4, 40, 40, 60, 40);
			PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
			document.open();

			// --------- COLORES CORPORATIVOS ---------
			Color azulOviedo = new Color(0, 51, 160);
			Color grisFondo = new Color(230, 230, 230);

			// --------- FUENTES ---------
			@SuppressWarnings("unused")
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, azulOviedo);
			Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, azulOviedo);
			Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
			Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, Color.GRAY);

			// ============================================================
			// FECHA Y HORA ACTUAL
			// ============================================================
			SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
			String fechaActual = fechaFormat.format(System.currentTimeMillis());
			String horaActual = horaFormat.format(System.currentTimeMillis());

			// ============================================================
			// ENCABEZADO REAL OVIEDO
			// ============================================================

			PdfPTable headerTable = new PdfPTable(2);
			headerTable.setWidthPercentage(100);
			headerTable.setWidths(new float[] { 20, 80 });

			try {
				InputStream logoStream = getClass().getClassLoader().getResourceAsStream("images/logo.png");
				if (logoStream != null) {
					byte[] bytes = logoStream.readAllBytes();
					Image logo = Image.getInstance(bytes);
					logo.scaleToFit(70, 70);

					PdfPCell logoCell = new PdfPCell(logo);
					logoCell.setBorder(Rectangle.NO_BORDER);
					logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerTable.addCell(logoCell);

				} else {
					PdfPCell empty = new PdfPCell(new Phrase(""));
					empty.setBorder(Rectangle.NO_BORDER);
					headerTable.addCell(empty);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			Paragraph infoClub = new Paragraph();
			infoClub.add(new Phrase("REAL OVIEDO C.F.\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, azulOviedo)));
			infoClub.add(new Phrase("Club Deportivo - Entidad Oficial\n", textFont));
			infoClub.add(new Phrase("C/ del Rosal, 10 — 33009 Oviedo, España\n", textFont));
			infoClub.add(new Phrase("www.realoviedo.es\n", textFont));

			PdfPCell clubCell = new PdfPCell(infoClub);
			clubCell.setBorder(Rectangle.NO_BORDER);
			clubCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			clubCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(clubCell);

			document.add(headerTable);
			document.add(new Paragraph("\n")); // separación

			// ============================================================
			// DATOS DE LA FACTURA
			// ============================================================

			PdfPTable facturaBox = new PdfPTable(1);
			facturaBox.setWidthPercentage(100);
			facturaBox.setSpacingBefore(15);

			PdfPCell facturaTitle = new PdfPCell(new Phrase("INFORMACIÓN DE LA FACTURA", sectionFont));
			facturaTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
			facturaTitle.setBackgroundColor(grisFondo);
			facturaTitle.setPadding(10);
			facturaTitle.setBorder(Rectangle.NO_BORDER);
			facturaBox.addCell(facturaTitle);
			String numeroFactura = String.format("2025-%04d", contadorFactura++);
			cliente.setNumeroFactura(numeroFactura);

			String fechaVencimiento = cliente.getFechaVencimiento() != null ? cliente.getFechaVencimiento() : "19/12/2025";

			String empresaNombre = cliente.getEmpresaNombre() != null ? cliente.getEmpresaNombre() : "Empresa de logística, S.L.";
			String empresaDireccion = cliente.getEmpresaDireccion() != null ? cliente.getEmpresaDireccion() : "Calle San Juan de la Luz, s/n";
			String empresaCif = cliente.getEmpresaCif() != null ? cliente.getEmpresaCif() : "B12345678";
			String empresaCpCiudad = cliente.getEmpresaCpCiudad() != null ? cliente.getEmpresaCpCiudad() : "28001, Madrid";
			String empresaEmail = cliente.getEmpresaEmail() != null ? cliente.getEmpresaEmail() : "info@empresalogisticasl.com";

			Paragraph bloqueFactura = new Paragraph();
			bloqueFactura.setSpacingBefore(5);

			bloqueFactura.add(new Phrase("Fecha de factura: ", labelFont));
			bloqueFactura.add(new Phrase(fechaActual + "\n", textFont));

			bloqueFactura.add(new Phrase("Número de factura: ", labelFont));
			bloqueFactura.add(new Phrase(numeroFactura + "\n", textFont));

			bloqueFactura.add(new Phrase("Fecha de vencimiento: ", labelFont));
			bloqueFactura.add(new Phrase(fechaVencimiento + "\n\n", textFont));

			bloqueFactura.add(new Phrase("Empresa emisora\n", sectionFont));
			bloqueFactura.add(new Phrase(empresaNombre + "\n", textFont));
			bloqueFactura.add(new Phrase("Dirección: " + empresaDireccion + "\n", textFont));
			bloqueFactura.add(new Phrase("CIF/NIF: " + empresaCif + "\n", textFont));
			bloqueFactura.add(new Phrase("CP y Ciudad: " + empresaCpCiudad + "\n", textFont));
			bloqueFactura.add(new Phrase("Email: " + empresaEmail + "\n", textFont));

			PdfPCell facturaBody = new PdfPCell(bloqueFactura);
			facturaBody.setPadding(12);
			facturaBody.setBorder(Rectangle.BOX);
			facturaBody.setBorderColor(azulOviedo);
			facturaBody.setBorderWidth(1.5f);

			facturaBox.addCell(facturaBody);
			document.add(facturaBox);

			document.add(new Paragraph("\n")); // separación

			// ============================================================
			// DATOS DEL CLIENTE
			// ============================================================

			Paragraph datos = new Paragraph("Datos del Cliente", sectionFont);
			datos.setSpacingAfter(10);
			document.add(datos);

			Paragraph infoCliente = new Paragraph();

			infoCliente.add(new Chunk("Fecha: ", labelFont));
			infoCliente.add(new Chunk(fechaActual, textFont));
			infoCliente.add(Chunk.NEWLINE);

			infoCliente.add(new Chunk("Hora: ", labelFont));
			infoCliente.add(new Chunk(horaActual, textFont));
			infoCliente.add(Chunk.NEWLINE);

			infoCliente.add(new Chunk("Nombre: ", labelFont));
			infoCliente.add(new Chunk(cliente.getNombre(), textFont));
			infoCliente.add(Chunk.NEWLINE);

			infoCliente.add(new Chunk("Dirección: ", labelFont));
			infoCliente.add(new Chunk(cliente.getDireccion(), textFont));
			infoCliente.add(Chunk.NEWLINE);

			infoCliente.add(new Chunk("Teléfono: ", labelFont));
			infoCliente.add(new Chunk(cliente.getTelefono(), textFont));
			infoCliente.add(Chunk.NEWLINE);

			infoCliente.add(new Chunk("Email: ", labelFont));
			infoCliente.add(new Chunk(cliente.getEmail(), textFont));
			infoCliente.setSpacingAfter(20);

			document.add(infoCliente);

			// ============================================================
			// TABLA ITEMS
			// ============================================================

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 45, 15, 20, 20 });
			table.setSpacingBefore(10);

			String[] headers = { "Descripción", "Cantidad", "Precio (€)", "Total (€)" };

			for (String h : headers) {
				PdfPCell cell = new PdfPCell(new Phrase(h, labelFont));
				cell.setBackgroundColor(grisFondo);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(6);
				table.addCell(cell);
			}

			for (FacturaItem item : items) {
				table.addCell(new PdfPCell(new Phrase(item.getDescripcion(), textFont)));
				table.addCell(new PdfPCell(new Phrase(String.valueOf(item.getCantidad()), textFont)));

				PdfPCell precioCell = new PdfPCell(new Phrase(String.format("%.2f", item.getPrecio()), textFont));
				precioCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(precioCell);

				PdfPCell totalCell = new PdfPCell(new Phrase(String.format("%.2f", item.getTotal()), textFont));
				totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(totalCell);
			}

			document.add(table);

			// ============================================================
			// TOTAL + IVA + TOTAL FINAL
			// ============================================================

			double subtotal = items.stream().mapToDouble(FacturaItem::getTotal).sum();
			double iva = subtotal * 0.21;
			double totalFinal = subtotal + iva;

			Paragraph subtotalP = new Paragraph("Subtotal: " + String.format("%.2f €", subtotal),
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, azulOviedo));
			subtotalP.setAlignment(Element.ALIGN_RIGHT);
			subtotalP.setSpacingBefore(10);
			document.add(subtotalP);

			Paragraph ivaP = new Paragraph("IVA (21%): " + String.format("%.2f €", iva),
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, azulOviedo));
			ivaP.setAlignment(Element.ALIGN_RIGHT);
			ivaP.setSpacingBefore(5);
			document.add(ivaP);

			Paragraph totalFinalP = new Paragraph("TOTAL FINAL: " + String.format("%.2f €", totalFinal),
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, azulOviedo));
			totalFinalP.setAlignment(Element.ALIGN_RIGHT);
			totalFinalP.setSpacingBefore(10);
			totalFinalP.setSpacingAfter(20);
			document.add(totalFinalP);

			// ============================================================
			// MENSAJE FINAL
			// ============================================================

			Paragraph nota = new Paragraph("Gracias por su confianza.\nREAL OVIEDO C.F.", footerFont);
			nota.setAlignment(Element.ALIGN_CENTER);
			nota.setSpacingBefore(20);
			document.add(nota);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nombreArchivo;
	}
}


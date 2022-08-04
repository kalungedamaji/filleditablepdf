package editablepdf;

import java.io.IOException;

public interface FillEditablePDF {
     void  loadPDF() throws IOException;
     void fillPDFField(String pdfFieldName, String pdfFieldValue);
     void savePDF() throws IOException;
}

package editablepdf;

import org.apache.log4j.Logger;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ApachePDFBoxPDFFormFiller implements  FillEditablePDF{

    private PDDocument  pdfDocument;
    private Set<COSDictionary> cosDictionarySet=new HashSet<>();
    private final static   Logger log = Logger.getLogger(ApachePDFBoxPDFFormFiller.class.getName());

    public void  loadPDF() throws IOException {
        log.debug("loadPDF is called");
        PDDocument  pDDocument = Loader.loadPDF(new File( "80.pdf"));
        this.pdfDocument=pDDocument;
        log.debug("loadPDF is completed");

    }

    public void fillPDFField(String pdfFieldName, String pdfFieldValue) {
        log.trace("fillPDFField with pdfFieldName : "+ pdfFieldName+" pdfFieldValue: "+pdfFieldValue);

        COSDictionary cosDictionary= getPdfDocument().getDocumentCatalog().getAcroForm().getField(pdfFieldName).getCOSObject();
        cosDictionary.setString(COSName.V ,pdfFieldValue);
        getCosDictionarySet().add(cosDictionary);
        log.trace("fillPDFField completed ");

    }


    private PDDocument getPdfDocument() {
        return pdfDocument;
    }

    private Set<COSDictionary> getCosDictionarySet() {
        return cosDictionarySet;
    }

    public void savePDF() throws IOException {
        log.debug("savePDF is called");
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources", "incrementalSave.pdf"));
        getPdfDocument().saveIncremental(fileOutputStream,getCosDictionarySet());
        getPdfDocument().close();
        log.debug("savePDF is completed");

    }
}

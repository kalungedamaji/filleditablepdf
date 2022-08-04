package awsrequesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import editablepdf.ApachePDFBoxPDFFormFiller;
import editablepdf.FillEditablePDF;
import fieldMapper.FieldMapper;
import jsonparser.JSONParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class FillPDFRequestHandler implements RequestHandler<Object,String> {
    private final static  Logger log = Logger.getLogger(FillPDFRequestHandler.class.getName());

    @Override
    public String handleRequest(Object s, Context context) {
       // main();
        return "sucess";
    }

   // public static void main(String[] args) {

   // }
   public static void main(String[] args) {
        log.info("fill Editable PDF process started");
        FillEditablePDF fillEditablePDF=new ApachePDFBoxPDFFormFiller();
        try {
            fillEditablePDF.loadPDF();
        } catch (IOException e) {
            log.error("Not able to fetch PDF Template ",e);
              return;
        }

        Map<String,String> jsonKeyToPDFFieldMap= null;
        try {
            jsonKeyToPDFFieldMap = new FieldMapper().getJsonKeyToPDFFieldMap();
        } catch (FileNotFoundException e) {
            log.error("Failed to get the jsonKeyToPDFField  Mapping ",e);
            return;
        }
        JsonObject jsonObject= null;
        try {
            jsonObject = new JSONParser().getJSONObject();
        } catch (FileNotFoundException e) {
            log.error("Failed to parse the JSON Input ",e);
            return;
        }

        log.debug("fill Editable called with JSON "+jsonObject.getAsString());
        log.debug("fill Editable called with Mapping "+jsonKeyToPDFFieldMap.toString());
        log.debug("fill Editable called with PDF Template ");


        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String jsonKeyName=entry.getKey();
            String pdfFieldName= jsonKeyToPDFFieldMap.get(jsonKeyName);
            if(entry.getValue() instanceof JsonPrimitive){
                fillEditablePDF.fillPDFField(pdfFieldName,entry.getValue().getAsString());
                log.trace("fill PDF Field : "+ pdfFieldName+"  JSON Key : "+jsonKeyName+"  value: "+entry.getValue().getAsString());
            }

        }

        try {
            fillEditablePDF.savePDF();
        } catch (IOException e) {
            log.error("Failed to save save the filled editable PDF ",e);
            return;
        }

        log.info("fill Editable PDF process ended");


    }


}

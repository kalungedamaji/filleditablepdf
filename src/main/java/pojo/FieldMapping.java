package pojo;

import com.opencsv.bean.CsvBindByPosition;

public class FieldMapping {



    @CsvBindByPosition(position = 0)
    private String  displayName;
    @CsvBindByPosition(position = 1)
    private String  pdfFieldName;
    @CsvBindByPosition(position = 2)
    private String  jsonKeyName;

    @Override
    public String toString() {
        return "FieldMapping{" +
                "displayName='" + displayName + '\'' +
                ", pdfFieldName='" + pdfFieldName + '\'' +
                ", jsonKeyName='" + jsonKeyName + '\'' +
                '}';
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPdfFieldName() {
        return pdfFieldName;
    }

    public void setPdfFieldName(String pdfFieldName) {
        this.pdfFieldName = pdfFieldName;
    }

    public String getJsonKeyName() {
        return jsonKeyName;
    }

    public void setJsonKeyName(String jsonKeyName) {
        this.jsonKeyName = jsonKeyName;
    }
}
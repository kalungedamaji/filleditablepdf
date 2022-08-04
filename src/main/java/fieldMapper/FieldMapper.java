package fieldMapper;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.log4j.Logger;
import pojo.FieldMapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Collectors;

public class FieldMapper {

    private final static Logger log = Logger.getLogger(FieldMapper.class.getName());

    public  Map<String,String> getJsonKeyToPDFFieldMap() throws FileNotFoundException {
        log.debug("getJsonKeyToPDFFieldMap is called");
        String fileName = "src/main/resources/mapping.csv";

        Map<String,String> jsonKeyToPDFFieldMap = (Map<String, String>) new CsvToBeanBuilder(new FileReader(fileName))
                .withType(FieldMapping.class)
                .build()
                .parse()
                .stream()
                .collect(
                  Collectors.toMap(
                          FieldMapping::getJsonKeyName,
                          FieldMapping::getPdfFieldName
                  ));

        log.debug("getJsonKeyToPDFFieldMap is completed and returned "+jsonKeyToPDFFieldMap.toString());
        return jsonKeyToPDFFieldMap;
    }
}

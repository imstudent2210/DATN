package com.graduate.touslestemp.util;

import com.graduate.touslestemp.domain.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportConfig {
    private int sheetIndex;
    private int startRow;
    private Class dataClass;
    private List<CellConfig> cellConfigList;

    public static final ExportConfig staffExport;
    static {
        staffExport = new ExportConfig();
        staffExport.setSheetIndex(0);
        staffExport.setStartRow(1);
        staffExport.setDataClass(Size.class);

        List<CellConfig> staffCellConfig = new ArrayList<>();

        staffCellConfig.add(new CellConfig(0, "size_id"));
        staffCellConfig.add(new CellConfig(1, "size"));
//        staffCellConfig.add(new CellConfig(2, "isActivated"));
//        staffCellConfig.add(new CellConfig(3, "phone"));
//        staffCellConfig.add(new CellConfig(4, "StaffGroup"));
//        staffCellConfig.add(new CellConfig(5, "Store"));
//        staffCellConfig.add(new CellConfig(6, "images"));

        staffExport.setCellConfigList(staffCellConfig);


    }
}

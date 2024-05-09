package com.akali.toolset;

import com.akali.toolset.model.word.InterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Slf4j
@SpringBootTest
class SweetToolsetApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("测试");
    }

    @Test
    void testReadWordDoc() {
        String wordDocPath = "C:\\temp\\智慧中台能力运营中心服务注册-XXXXX机构接口规范-V2.docx";
        try (FileInputStream fis = new FileInputStream(wordDocPath)) {
            XWPFDocument document = new XWPFDocument(fis);
            List<IBodyElement> bodyElements = document.getBodyElements();
            // 第一步：过滤出需要的文档内容
            List<IBodyElement> validElements = new ArrayList<>();
            boolean flag = false;
            for (IBodyElement bodyElement : bodyElements) {
                if (bodyElement instanceof XWPFParagraph) {
                    if (((XWPFParagraph) bodyElement).getParagraphText().equals("接口清单")) {
                        flag = true;
                    }

                    if (((XWPFParagraph) bodyElement).getParagraphText().equals("附录")) {
                        flag = false;
                    }
                }

                if (flag) {
                    validElements.add(bodyElement);
                }
            }

            List<InterfaceInfo> interfaceInfos = new ArrayList<>();
            for (IBodyElement validElement : validElements) {
                // 收集数据

                if (validElement instanceof XWPFParagraph) {
                    String paragraphText = ((XWPFParagraph) validElement).getParagraphText();
                    if (StringUtils.hasLength(paragraphText)) {
                        System.out.println("段落：" + paragraphText);
                    }
                    InterfaceInfo interfaceInfo = null;
                    if (StringUtils.hasLength(paragraphText) && "接口".equals(paragraphText.substring(paragraphText.length() - 2))) {
                        interfaceInfo = new InterfaceInfo();
                    }

                    handleParagraph(paragraphText, interfaceInfo);
                } else if (validElement instanceof XWPFTable) {
                    System.out.println("我是表格");
                    List<XWPFTableRow> rows = ((XWPFTable) validElement).getRows();
//                    for (XWPFTableRow row : rows) {
//                        List<XWPFTableCell> tableCells = row.getTableCells();
//                        for (XWPFTableCell tableCell : tableCells) {
//                            System.out.println("表格内容：" + tableCell.getText());
//                        }
//                    }
                }
            }


        } catch (Exception e) {
            log.error("读取word07文件失败, errorMsg: {}", e.getMessage(), e);
        }
    }

    private void handleParagraph(String paragraphText, InterfaceInfo interfaceInfo) {
        if (paragraphText.contains("接口")) {
//            paragraphMap.put("接口名称", paragraphText);
            interfaceInfo.setInterfaceName(paragraphText);
        } else if ("接口说明".equals(paragraphText)) {
            interfaceInfo.setInterfaceDesc(null);
        } else if (paragraphText.contains("本地接口编码：")) {
//            paragraphMap.put("本地接口编码", paragraphText);
            interfaceInfo.setLocalInterfaceCode(paragraphText);
        } else if (paragraphText.contains("服务英文名：")) {
//            paragraphMap.put("服务英文名", paragraphText);
            interfaceInfo.setServiceEnName(paragraphText);
        } else if (paragraphText.contains("URL：")) {
//            paragraphMap.put("URL", paragraphText);
            interfaceInfo.setUrl(paragraphText);
        } else if (paragraphText.contains("HTTP方法：")) {
//            paragraphMap.put("HTTP方法", paragraphText);
            interfaceInfo.setHttpMethod(paragraphText);
        } else if (paragraphText.contains("接口类型：")) {
//            paragraphMap.put("接口类型", paragraphText);
            interfaceInfo.setInterfaceType(paragraphText);
        }

//        return paragraphMap;
    }

    private static Map<String, Object> getNextTable(XWPFParagraph paragraph, XWPFDocument document) {
        // 由于POI API没有直接的方法来获取段落之后的第一个表格，
        // 我们需要遍历文档中的表格，并检查它们是否在段落之后
        Map<String, Object> tableMap = new HashMap<>();
        int paragraphIndex = document.getPosOfParagraph(paragraph);
        for (XWPFTable table : document.getTables()) {
            int tableIndex = document.getPosOfTable(table);
            if (tableIndex > paragraphIndex) {
//                return table; // 假设我们只需要第一个在段落之后的表格
                tableMap.put(paragraph.getParagraphText(), table);
            }
        }

        return tableMap; // 如果没有找到表格，则返回null
    }

    @Test
    void test01() {
        String testStr = "订单查询接口";
        System.out.println(testStr.substring(testStr.length() - 2));
        System.out.println(testStr.substring(0, testStr.length() - 2));
    }
}

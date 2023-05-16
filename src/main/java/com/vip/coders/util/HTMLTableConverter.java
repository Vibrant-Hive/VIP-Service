package com.vip.coders.util;


import java.util.List;

public class HTMLTableConverter {

    public static String convertListToHTMLTable(List<?> modelObjects) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder htmlTable = new StringBuilder();

        htmlTable.append("<table>");

        // Add the table header row
        htmlTable.append("<thead>");

        String[] fieldNames = FieldNamesGetter.getFieldNames(modelObjects.get(0).getClass());
        for (String columnName : fieldNames) {
            htmlTable.append("<th>");
            htmlTable.append(columnName);
            htmlTable.append("</th>");
        }

        htmlTable.append("</thead>");

        // Add the table body rows
        for (Object modelObject : modelObjects) {
            htmlTable.append("<tr>");

            for (String fieldName : fieldNames) {
                Object columnValue = String.valueOf(GetterSetter.callGetter(modelObject, fieldName));
                htmlTable.append("<td>");
                htmlTable.append(columnValue);
                htmlTable.append("</td>");
            }

            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");

        return htmlTable.toString();
    }
}

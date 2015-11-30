package io.kombat.domain.dao;

import java.util.Map;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 30/11/15.
 */
public enum FilterType {
    EXACT("AND %s = %s"),
    LIKE("AND %s like '%s'"),
    GREATER(" AND %s >= %s"),
    BETWEEN(" AND %s BETWEEN %s AND %s"),
    TIME_GREATER("AND %s >= TO_TIMESTAMP('%s','YYYY-MM-DD\"T\"HH24:MI')"),
    TIME_BETWEEN("AND %s BETWEEN TO_TIMESTAMP('%s','YYYY-MM-DD\"T\"HH24:MI') AND TO_TIMESTAMP('%s','YYYY-MM-DD\"T\"HH24:MI')");

    private final String filter;

    FilterType(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public static String parse(String name, String column, Map<String, String[]> params, FilterType type) {
        String[] field = params.get(name);

        if (field != null && field.length > 0) {
            String where = field[0];
            if (!where.isEmpty()) {
                return String.format(type.getFilter(), column, (type.equals(FilterType.LIKE) ? "%" + where + "%" : where));
            }
        }

        return "";
    }

}
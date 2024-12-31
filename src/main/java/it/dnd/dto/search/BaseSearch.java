package it.dnd.dto.search;

import io.ebean.ExpressionList;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearch {

    @QueryParam("page")
    @DefaultValue("1")
    protected int page;

    @QueryParam("size")
    @DefaultValue("20")
    protected int size;

    @QueryParam("sort")
    protected String sort;

    @QueryParam("descending")
    protected boolean descending;

    public <T> ExpressionList<T> paginationOrderAndSort(ExpressionList<T> query) {
        int rows = this.getSize();
        int offset = (this.getPage() - 1) * rows;
        String order = this.getSort();

        if (order != null) {
            query.orderBy(order);
        } else {
            query.orderById(true);
        }

        query.setFirstRow(offset).setMaxRows(rows);
        return query;
    }
}

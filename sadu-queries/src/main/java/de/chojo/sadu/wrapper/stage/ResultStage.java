/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sadu.wrapper.stage;

import de.chojo.sadu.exceptions.ThrowingFunction;
import de.chojo.sadu.wrapper.QueryBuilder;
import de.chojo.sadu.wrapper.util.Row;

import java.sql.SQLException;

/**
 * Represents a ResultStage of a {@link QueryBuilder}.
 * <p>
 * A ResultStage is used to read the result set, perform updates or append another query.
 *
 * @param <T> type of ResultStage
 */
public interface ResultStage<T> {

    /**
     * Extract results from the current row.
     *
     * @param mapper mapper to map the current row.
     * @return The {@link QueryBuilder} in a {@link RetrievalStage} to retrieve the row/s.
     */
    RetrievalStage<T> readRow(ThrowingFunction<T, Row, SQLException> mapper);

    /**
     * Mark this query as update query.
     *
     * @return The {@link QueryBuilder} in a {@link UpdateStage} to update the data.
     */
    UpdateStage update();

    /**
     * Mark this query as deletion query. Alias for {@link #update()}
     *
     * @return The {@link QueryBuilder} in a {@link UpdateStage} to update the data.
     */
    default UpdateStage delete() {
        return update();
    }

    /**
     * Insert data into a table. Alias for {@link #update()}
     *
     * @return The {@link QueryBuilder} in a {@link InsertStage} to update the data.
     */
    InsertStage insert();


    /**
     * Append another query to the query builder.
     *
     * @return The {@link QueryBuilder} in a {@link QueryStage}
     */
    QueryStage<T> append();
}
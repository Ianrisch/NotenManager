package org.notenmanager.Utils.Consumer;

import java.util.Objects;

public interface OConsumer<Output> {

    /**
     * Performs this operation.
     *
     * @return {@code Output Type}
     */
    Output accept();

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code IOConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default OConsumer<Output> andThen(OConsumer<Output> after) {
        Objects.requireNonNull(after);
        return () -> {
            accept();
            return after.accept();
        };
    }
}

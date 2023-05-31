package org.notenmanager.Utils.Consumer;

import java.util.Objects;

public interface IOConsumer<Input, Output> {
    /**
     * Performs this operation on the given argument.
     *
     * @param input the input argument
     * @return {@code Output Type}
     */
    Output accept(Input input);

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
    default IOConsumer<Input, Output> andThen(IOConsumer<Input, Output> after) {
        Objects.requireNonNull(after);
        return (Input t) -> {
            accept(t);
            return  after.accept(t);
        };
    }
}

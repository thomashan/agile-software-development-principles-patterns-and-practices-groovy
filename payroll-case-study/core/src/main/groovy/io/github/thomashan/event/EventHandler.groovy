package io.github.thomashan.event

import io.github.thomashan.domain.AggregateRoot

@FunctionalInterface
interface EventHandler<E extends Event, A extends AggregateRoot> {
    Optional<A> plus(E event)
}

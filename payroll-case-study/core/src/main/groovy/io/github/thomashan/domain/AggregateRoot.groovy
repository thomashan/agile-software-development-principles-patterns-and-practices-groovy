package io.github.thomashan.domain

import io.github.thomashan.event.Event

interface AggregateRoot<A extends AggregateRoot> {
    /**
     * Apply the aggregate event to the aggregate root.
     * This method has limitation as it will only be able to apply the event to a single aggregate root.
     * Also it won't be able to handle events like add whether there is no handle on the aggregate root.
     * FIXME: We may need a domain service or an event handler which orchestrates with other aggregate root.
     * But overall this is the most elegant way of applying the event to the aggregate root
     * @param event
     * @return
     */
    A plus(Event event)
}

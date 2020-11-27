package io.github.thomashan.event

import java.time.Instant

trait Event {
    final Instant created
    final Instant processed
}

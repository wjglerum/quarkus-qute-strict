package nl.wjglerum;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style
public interface Metrics {

    Count requests();

    Count responses();
}

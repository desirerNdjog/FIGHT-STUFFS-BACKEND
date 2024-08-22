package org.accenture.utils.complex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: desirejuniorndjog.
 * @created: 21/08/2024 : 17:45
 * @project: FIGHTSTUFF
 */

@Getter
@Setter
@AllArgsConstructor
public class Tuplet<F, S, T> {
    private F frist;
    private S seconde;
    private T third;
}

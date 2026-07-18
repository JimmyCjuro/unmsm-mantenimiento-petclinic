package org.springframework.samples.petclinic.customers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Regression tests for {@link Owner} after the Checkstyle HiddenField refactoring
 * (Week 5): setter parameters shadowed the class fields, which could silently
 * produce wrong assignments (e.g. a self-assignment of the parameter instead of
 * assigning to the field). These tests pin down the correct setter behavior.
 */
class OwnerTest {

    private Owner owner;

    @BeforeEach
    void setup() {
        owner = new Owner();
    }

    // --- 1. Each setter assigns the value to its corresponding field ---
    // HiddenField regression: if the fix had left a self-assignment of the
    // parameter (param = param), the getter would return null instead of the value.

    @Test
    void shouldSetFirstName() {
        owner.setFirstName("Juan");
        assertThat(owner.getFirstName()).isEqualTo("Juan");
    }

    @Test
    void shouldSetLastName() {
        owner.setLastName("Perez");
        assertThat(owner.getLastName()).isEqualTo("Perez");
    }

    @Test
    void shouldSetAddress() {
        owner.setAddress("Av. Universitaria 1801");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
    }

    @Test
    void shouldSetCity() {
        owner.setCity("Lima");
        assertThat(owner.getCity()).isEqualTo("Lima");
    }

    @Test
    void shouldSetTelephone() {
        owner.setTelephone("999888777");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
    }

    // --- 2. A setter must not overwrite or affect other fields ---
    // Critical HiddenField case: after renaming the shadowed parameters, an
    // incorrect assignment could target the wrong field. Each test fills every
    // field, mutates a single one, and verifies the rest stayed intact.

    @Test
    void shouldNotAffectOtherFieldsWhenSettingFirstName() {
        fillAllFields();
        owner.setFirstName("Cambiado");

        assertThat(owner.getFirstName()).isEqualTo("Cambiado");
        assertThat(owner.getLastName()).isEqualTo("Perez");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
        assertThat(owner.getCity()).isEqualTo("Lima");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
    }

    @Test
    void shouldNotAffectOtherFieldsWhenSettingLastName() {
        fillAllFields();
        owner.setLastName("Cambiado");

        assertThat(owner.getFirstName()).isEqualTo("Juan");
        assertThat(owner.getLastName()).isEqualTo("Cambiado");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
        assertThat(owner.getCity()).isEqualTo("Lima");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
    }

    @Test
    void shouldNotAffectOtherFieldsWhenSettingAddress() {
        fillAllFields();
        owner.setAddress("Cambiado");

        assertThat(owner.getFirstName()).isEqualTo("Juan");
        assertThat(owner.getLastName()).isEqualTo("Perez");
        assertThat(owner.getAddress()).isEqualTo("Cambiado");
        assertThat(owner.getCity()).isEqualTo("Lima");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
    }

    @Test
    void shouldNotAffectOtherFieldsWhenSettingCity() {
        fillAllFields();
        owner.setCity("Cambiado");

        assertThat(owner.getFirstName()).isEqualTo("Juan");
        assertThat(owner.getLastName()).isEqualTo("Perez");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
        assertThat(owner.getCity()).isEqualTo("Cambiado");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
    }

    @Test
    void shouldNotAffectOtherFieldsWhenSettingTelephone() {
        fillAllFields();
        owner.setTelephone("000000000");

        assertThat(owner.getFirstName()).isEqualTo("Juan");
        assertThat(owner.getLastName()).isEqualTo("Perez");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
        assertThat(owner.getCity()).isEqualTo("Lima");
        assertThat(owner.getTelephone()).isEqualTo("000000000");
    }

    // --- 3. Null and empty values are accepted without unexpected exceptions ---
    // The setters have no explicit validation (the @NotBlank/@Digits constraints
    // only apply when Bean Validation runs), so plain assignment must not throw.

    @Test
    void shouldAcceptNullValuesWithoutThrowing() {
        assertDoesNotThrow(() -> {
            owner.setFirstName(null);
            owner.setLastName(null);
            owner.setAddress(null);
            owner.setCity(null);
            owner.setTelephone(null);
        });

        assertThat(owner.getFirstName()).isNull();
        assertThat(owner.getLastName()).isNull();
        assertThat(owner.getAddress()).isNull();
        assertThat(owner.getCity()).isNull();
        assertThat(owner.getTelephone()).isNull();
    }

    @Test
    void shouldAcceptEmptyStringsWithoutThrowing() {
        assertDoesNotThrow(() -> {
            owner.setFirstName("");
            owner.setLastName("");
            owner.setAddress("");
            owner.setCity("");
            owner.setTelephone("");
        });

        assertThat(owner.getFirstName()).isEmpty();
        assertThat(owner.getLastName()).isEmpty();
        assertThat(owner.getAddress()).isEmpty();
        assertThat(owner.getCity()).isEmpty();
        assertThat(owner.getTelephone()).isEmpty();
    }

    // --- 4. A fully populated Owner keeps the integrity of all values ---

    @Test
    void shouldKeepIntegrityOfAllFieldsWhenFullyPopulated() {
        fillAllFields();

        assertThat(owner.getFirstName()).isEqualTo("Juan");
        assertThat(owner.getLastName()).isEqualTo("Perez");
        assertThat(owner.getAddress()).isEqualTo("Av. Universitaria 1801");
        assertThat(owner.getCity()).isEqualTo("Lima");
        assertThat(owner.getTelephone()).isEqualTo("999888777");
        // The id has no setter, so it must remain unassigned on a new instance
        assertThat(owner.getId()).isNull();
    }

    private void fillAllFields() {
        owner.setFirstName("Juan");
        owner.setLastName("Perez");
        owner.setAddress("Av. Universitaria 1801");
        owner.setCity("Lima");
        owner.setTelephone("999888777");
    }
}

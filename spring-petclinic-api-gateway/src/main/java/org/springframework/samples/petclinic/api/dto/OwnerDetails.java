/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Data Transfer Object representing detailed information about an owner,
 * including their associated pets.
 *
 * @author Maciej Szarlinski
 */
public record OwnerDetails(
    int id,
    String firstName,
    String lastName,
    String address,
    String city,
    String telephone,
    List<PetDetails> pets) {

    /**
     * Returns a list of pet IDs associated with this owner.
     *
     * @return list of pet IDs
     */
    @JsonIgnore
    public List<Integer> getPetIds() {
        return pets.stream()
            .map(PetDetails::id)
            .toList();
    }


    /**
     * Builder for constructing {@link OwnerDetails} instances.
     */
    public static final class OwnerDetailsBuilder {

        /** The owner's unique identifier. */
        private int id;

        /** The owner's first name. */
        private String firstName;

        /** The owner's last name. */
        private String lastName;

        /** The owner's street address. */
        private String address;

        /** The owner's city. */
        private String city;

        /** The owner's telephone number. */
        private String telephone;

        /** The list of pets associated with the owner. */
        private List<PetDetails> pets;

        private OwnerDetailsBuilder() {
        }

        /**
         * Creates a new {@link OwnerDetailsBuilder} instance.
         *
         * @return a new builder instance
         */
        public static OwnerDetailsBuilder anOwnerDetails() {
            return new OwnerDetailsBuilder();
        }

        /**
         * Sets the owner's unique identifier.
         *
         * @param newId the owner ID to set
         * @return this builder
         */
        public OwnerDetailsBuilder id(int newId) {
            this.id = newId;
            return this;
        }

        /**
         * Sets the owner's first name.
         *
         * @param newFirstName the first name to set
         * @return this builder
         */
        public OwnerDetailsBuilder firstName(String newFirstName) {
            this.firstName = newFirstName;
            return this;
        }

        /**
         * Sets the owner's last name.
         *
         * @param newLastName the last name to set
         * @return this builder
         */
        public OwnerDetailsBuilder lastName(String newLastName) {
            this.lastName = newLastName;
            return this;
        }

        /**
         * Sets the owner's street address.
         *
         * @param newAddress the address to set
         * @return this builder
         */
        public OwnerDetailsBuilder address(String newAddress) {
            this.address = newAddress;
            return this;
        }

        /**
         * Sets the owner's city.
         *
         * @param newCity the city to set
         * @return this builder
         */
        public OwnerDetailsBuilder city(String newCity) {
            this.city = newCity;
            return this;
        }

        /**
         * Sets the owner's telephone number.
         *
         * @param newTelephone the telephone number to set
         * @return this builder
         */
        public OwnerDetailsBuilder telephone(String newTelephone) {
            this.telephone = newTelephone;
            return this;
        }

        /**
         * Sets the list of pets associated with the owner.
         *
         * @param newPets the list of pets to set
         * @return this builder
         */
        public OwnerDetailsBuilder pets(List<PetDetails> newPets) {
            this.pets = newPets;
            return this;
        }

        /**
         * Builds and returns a new {@link OwnerDetails} instance.
         *
         * @return the constructed {@link OwnerDetails}
         */
        public OwnerDetails build() {
            return new OwnerDetails(id, firstName, lastName, address, city, telephone, pets);
        }
    }
}

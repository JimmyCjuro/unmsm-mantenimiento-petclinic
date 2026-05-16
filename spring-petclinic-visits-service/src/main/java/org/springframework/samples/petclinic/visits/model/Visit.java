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
package org.springframework.samples.petclinic.visits.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 * @author Maciej Szarlinski
 * @author Ramazan Sakin
 */
@Entity
@Table(name = "visits")
public final class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

    @Size(max = 8192)
    @Column(name = "description")
    private String description;

    @Column(name = "pet_id")
    private int petId;

    public Integer getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setPetId(int newPetId) {
        this.petId = newPetId;
    }


    public static final class VisitBuilder {
        private Integer id;
        private Date date;
        private @Size(max = 8192) String description;
        private int petId;

        private VisitBuilder() {
        }

        public static VisitBuilder aVisit() {
            return new VisitBuilder();
        }

        public VisitBuilder id(Integer newId) {
            this.id = newId;
            return this;
        }

        public VisitBuilder date(Date newDate) {
            this.date = newDate;
            return this;
        }

        public VisitBuilder description(String newDescription) {
            this.description = newDescription;
            return this;
        }

        public VisitBuilder petId(int newPetId) {
            this.petId = newPetId;
            return this;
        }

        public Visit build() {
            Visit visit = new Visit();
            visit.setId(id);
            visit.setDate(date);
            visit.setDescription(description);
            visit.setPetId(petId);
            return visit;
        }
    }
}

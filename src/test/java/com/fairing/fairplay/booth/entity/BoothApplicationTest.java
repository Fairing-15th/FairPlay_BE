package com.fairing.fairplay.booth.entity;

import com.fairing.fairplay.event.entity.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoothApplication Entity Tests")
class BoothApplicationTest {

    private BoothApplication boothApplication;
    private Event mockEvent;
    private BoothApplicationStatusCode mockStatusCode;
    private BoothPaymentStatusCode mockPaymentStatusCode;

    @BeforeEach
    void setUp() {
        boothApplication = new BoothApplication();
        mockEvent = new Event();
        mockStatusCode = new BoothApplicationStatusCode();
        mockPaymentStatusCode = new BoothPaymentStatusCode();
    }

    @Nested
    @DisplayName("Entity Creation and Basic Properties")
    class EntityCreationTests {

        @Test
        @DisplayName("Should create BoothApplication with default constructor")
        void shouldCreateBoothApplicationWithDefaultConstructor() {
            // Given & When
            BoothApplication application = new BoothApplication();

            // Then
            assertThat(application).isNotNull();
            assertThat(application.getId()).isNull();
            assertThat(application.getEvent()).isNull();
            assertThat(application.getBoothEmail()).isNull();
            assertThat(application.getBoothDescription()).isNull();
            assertThat(application.getManagerName()).isNull();
            assertThat(application.getEmail()).isNull();
            assertThat(application.getContactNumber()).isNull();
            assertThat(application.getOfficialUrl()).isNull();
            assertThat(application.getApplyAt()).isNull();
            assertThat(application.getAdminComment()).isNull();
            assertThat(application.getBoothApplicationStatusCode()).isNull();
            assertThat(application.getBoothPaymentStatusCode()).isNull();
            assertThat(application.getStatusUpdatedAt()).isNull();
        }

        @Test
        @DisplayName("Should have proper JPA annotations on class")
        void shouldHaveProperJPAAnnotationsOnClass() {
            // Given
            Class<BoothApplication> clazz = BoothApplication.class;

            // Then
            assertThat(clazz.isAnnotationPresent(Entity.class)).isTrue();
        }

        @Test
        @DisplayName("Should have proper Lombok annotations")
        void shouldHaveProperLombokAnnotations() {
            // Given
            Class<BoothApplication> clazz = BoothApplication.class;

            // Then
            assertThat(clazz.isAnnotationPresent(Getter.class)).isTrue();
            assertThat(clazz.isAnnotationPresent(NoArgsConstructor.class)).isTrue();
        }
    }

    @Nested
    @DisplayName("ID Field Tests")
    class IdFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on id field")
        void shouldHaveProperJPAAnnotationsOnIdField() throws NoSuchFieldException {
            // Given
            Field idField = BoothApplication.class.getDeclaredField("id");

            // Then
            assertThat(idField.isAnnotationPresent(Id.class)).isTrue();
            assertThat(idField.isAnnotationPresent(GeneratedValue.class)).isTrue();
            assertThat(idField.isAnnotationPresent(Column.class)).isTrue();

            GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
            assertThat(generatedValue.strategy()).isEqualTo(GenerationType.IDENTITY);

            Column column = idField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("booth_application_id");
        }

        @Test
        @DisplayName("Should have correct field type for id")
        void shouldHaveCorrectFieldTypeForId() throws NoSuchFieldException {
            // Given
            Field idField = BoothApplication.class.getDeclaredField("id");

            // Then
            assertThat(idField.getType()).isEqualTo(Long.class);
        }

        @Test
        @DisplayName("Should return null id for newly created entity")
        void shouldReturnNullIdForNewlyCreatedEntity() {
            // Given & When
            Long id = boothApplication.getId();

            // Then
            assertThat(id).isNull();
        }
    }

    @Nested
    @DisplayName("Event Relationship Tests")
    class EventRelationshipTests {

        @Test
        @DisplayName("Should have proper JPA annotations on event field")
        void shouldHaveProperJPAAnnotationsOnEventField() throws NoSuchFieldException {
            // Given
            Field eventField = BoothApplication.class.getDeclaredField("event");

            // Then
            assertThat(eventField.isAnnotationPresent(ManyToOne.class)).isTrue();
            assertThat(eventField.isAnnotationPresent(JoinColumn.class)).isTrue();

            ManyToOne manyToOne = eventField.getAnnotation(ManyToOne.class);
            assertThat(manyToOne.fetch()).isEqualTo(FetchType.LAZY);

            JoinColumn joinColumn = eventField.getAnnotation(JoinColumn.class);
            assertThat(joinColumn.name()).isEqualTo("event_id");
            assertThat(joinColumn.nullable()).isFalse();
        }

        @Test
        @DisplayName("Should have correct field type for event")
        void shouldHaveCorrectFieldTypeForEvent() throws NoSuchFieldException {
            // Given
            Field eventField = BoothApplication.class.getDeclaredField("event");

            // Then
            assertThat(eventField.getType()).isEqualTo(Event.class);
        }

        @Test
        @DisplayName("Should return null event initially")
        void shouldReturnNullEventInitially() {
            // Given & When
            Event event = boothApplication.getEvent();

            // Then
            assertThat(event).isNull();
        }
    }

    @Nested
    @DisplayName("Booth Email Field Tests")
    class BoothEmailFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on boothEmail field")
        void shouldHaveProperJPAAnnotationsOnBoothEmailField() throws NoSuchFieldException {
            // Given
            Field boothEmailField = BoothApplication.class.getDeclaredField("boothEmail");

            // Then
            assertThat(boothEmailField.isAnnotationPresent(Column.class)).isTrue();

            Column column = boothEmailField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("booth_email");
            assertThat(column.nullable()).isFalse();
            assertThat(column.length()).isEqualTo(100);
        }

        @Test
        @DisplayName("Should have correct field type for boothEmail")
        void shouldHaveCorrectFieldTypeForBoothEmail() throws NoSuchFieldException {
            // Given
            Field boothEmailField = BoothApplication.class.getDeclaredField("boothEmail");

            // Then
            assertThat(boothEmailField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null boothEmail initially")
        void shouldReturnNullBoothEmailInitially() {
            // Given & When
            String boothEmail = boothApplication.getBoothEmail();

            // Then
            assertThat(boothEmail).isNull();
        }
    }

    @Nested
    @DisplayName("Booth Description Field Tests")
    class BoothDescriptionFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on boothDescription field")
        void shouldHaveProperJPAAnnotationsOnBoothDescriptionField() throws NoSuchFieldException {
            // Given
            Field boothDescriptionField = BoothApplication.class.getDeclaredField("boothDescription");

            // Then
            assertThat(boothDescriptionField.isAnnotationPresent(Column.class)).isTrue();

            Column column = boothDescriptionField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("booth_description");
            assertThat(column.columnDefinition()).isEqualTo("TEXT");
        }

        @Test
        @DisplayName("Should have correct field type for boothDescription")
        void shouldHaveCorrectFieldTypeForBoothDescription() throws NoSuchFieldException {
            // Given
            Field boothDescriptionField = BoothApplication.class.getDeclaredField("boothDescription");

            // Then
            assertThat(boothDescriptionField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null boothDescription initially")
        void shouldReturnNullBoothDescriptionInitially() {
            // Given & When
            String boothDescription = boothApplication.getBoothDescription();

            // Then
            assertThat(boothDescription).isNull();
        }
    }

    @Nested
    @DisplayName("Manager Name Field Tests")
    class ManagerNameFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on managerName field")
        void shouldHaveProperJPAAnnotationsOnManagerNameField() throws NoSuchFieldException {
            // Given
            Field managerNameField = BoothApplication.class.getDeclaredField("managerName");

            // Then
            assertThat(managerNameField.isAnnotationPresent(Column.class)).isTrue();

            Column column = managerNameField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("manager_name");
            assertThat(column.nullable()).isFalse();
            assertThat(column.length()).isEqualTo(20);
        }

        @Test
        @DisplayName("Should have correct field type for managerName")
        void shouldHaveCorrectFieldTypeForManagerName() throws NoSuchFieldException {
            // Given
            Field managerNameField = BoothApplication.class.getDeclaredField("managerName");

            // Then
            assertThat(managerNameField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null managerName initially")
        void shouldReturnNullManagerNameInitially() {
            // Given & When
            String managerName = boothApplication.getManagerName();

            // Then
            assertThat(managerName).isNull();
        }
    }

    @Nested
    @DisplayName("Email Field Tests")
    class EmailFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on email field")
        void shouldHaveProperJPAAnnotationsOnEmailField() throws NoSuchFieldException {
            // Given
            Field emailField = BoothApplication.class.getDeclaredField("email");

            // Then
            assertThat(emailField.isAnnotationPresent(Column.class)).isTrue();

            Column column = emailField.getAnnotation(Column.class);
            assertThat(column.nullable()).isFalse();
            assertThat(column.length()).isEqualTo(100);
        }

        @Test
        @DisplayName("Should have correct field type for email")
        void shouldHaveCorrectFieldTypeForEmail() throws NoSuchFieldException {
            // Given
            Field emailField = BoothApplication.class.getDeclaredField("email");

            // Then
            assertThat(emailField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null email initially")
        void shouldReturnNullEmailInitially() {
            // Given & When
            String email = boothApplication.getEmail();

            // Then
            assertThat(email).isNull();
        }
    }

    @Nested
    @DisplayName("Contact Number Field Tests")
    class ContactNumberFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on contactNumber field")
        void shouldHaveProperJPAAnnotationsOnContactNumberField() throws NoSuchFieldException {
            // Given
            Field contactNumberField = BoothApplication.class.getDeclaredField("contactNumber");

            // Then
            assertThat(contactNumberField.isAnnotationPresent(Column.class)).isTrue();

            Column column = contactNumberField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("contact_number");
            assertThat(column.nullable()).isFalse();
            assertThat(column.length()).isEqualTo(20);
        }

        @Test
        @DisplayName("Should have correct field type for contactNumber")
        void shouldHaveCorrectFieldTypeForContactNumber() throws NoSuchFieldException {
            // Given
            Field contactNumberField = BoothApplication.class.getDeclaredField("contactNumber");

            // Then
            assertThat(contactNumberField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null contactNumber initially")
        void shouldReturnNullContactNumberInitially() {
            // Given & When
            String contactNumber = boothApplication.getContactNumber();

            // Then
            assertThat(contactNumber).isNull();
        }
    }

    @Nested
    @DisplayName("Official URL Field Tests")
    class OfficialUrlFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on officialUrl field")
        void shouldHaveProperJPAAnnotationsOnOfficialUrlField() throws NoSuchFieldException {
            // Given
            Field officialUrlField = BoothApplication.class.getDeclaredField("officialUrl");

            // Then
            assertThat(officialUrlField.isAnnotationPresent(Column.class)).isTrue();

            Column column = officialUrlField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("official_url");
            assertThat(column.nullable()).isFalse();
            assertThat(column.length()).isEqualTo(512);
        }

        @Test
        @DisplayName("Should have correct field type for officialUrl")
        void shouldHaveCorrectFieldTypeForOfficialUrl() throws NoSuchFieldException {
            // Given
            Field officialUrlField = BoothApplication.class.getDeclaredField("officialUrl");

            // Then
            assertThat(officialUrlField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null officialUrl initially")
        void shouldReturnNullOfficialUrlInitially() {
            // Given & When
            String officialUrl = boothApplication.getOfficialUrl();

            // Then
            assertThat(officialUrl).isNull();
        }
    }

    @Nested
    @DisplayName("Apply At Field Tests")
    class ApplyAtFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on applyAt field")
        void shouldHaveProperJPAAnnotationsOnApplyAtField() throws NoSuchFieldException {
            // Given
            Field applyAtField = BoothApplication.class.getDeclaredField("applyAt");

            // Then
            assertThat(applyAtField.isAnnotationPresent(Column.class)).isTrue();

            Column column = applyAtField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("apply_at");
            assertThat(column.columnDefinition()).isEqualTo("DATETIME DEFAULT CURRENT_TIMESTAMP");
        }

        @Test
        @DisplayName("Should have correct field type for applyAt")
        void shouldHaveCorrectFieldTypeForApplyAt() throws NoSuchFieldException {
            // Given
            Field applyAtField = BoothApplication.class.getDeclaredField("applyAt");

            // Then
            assertThat(applyAtField.getType()).isEqualTo(LocalDateTime.class);
        }

        @Test
        @DisplayName("Should return null applyAt initially")
        void shouldReturnNullApplyAtInitially() {
            // Given & When
            LocalDateTime applyAt = boothApplication.getApplyAt();

            // Then
            assertThat(applyAt).isNull();
        }
    }

    @Nested
    @DisplayName("Admin Comment Field Tests")
    class AdminCommentFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on adminComment field")
        void shouldHaveProperJPAAnnotationsOnAdminCommentField() throws NoSuchFieldException {
            // Given
            Field adminCommentField = BoothApplication.class.getDeclaredField("adminComment");

            // Then
            assertThat(adminCommentField.isAnnotationPresent(Column.class)).isTrue();

            Column column = adminCommentField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("admin_comment");
            assertThat(column.columnDefinition()).isEqualTo("TEXT");
        }

        @Test
        @DisplayName("Should have correct field type for adminComment")
        void shouldHaveCorrectFieldTypeForAdminComment() throws NoSuchFieldException {
            // Given
            Field adminCommentField = BoothApplication.class.getDeclaredField("adminComment");

            // Then
            assertThat(adminCommentField.getType()).isEqualTo(String.class);
        }

        @Test
        @DisplayName("Should return null adminComment initially")
        void shouldReturnNullAdminCommentInitially() {
            // Given & When
            String adminComment = boothApplication.getAdminComment();

            // Then
            assertThat(adminComment).isNull();
        }
    }

    @Nested
    @DisplayName("Booth Application Status Code Relationship Tests")
    class BoothApplicationStatusCodeRelationshipTests {

        @Test
        @DisplayName("Should have proper JPA annotations on boothApplicationStatusCode field")
        void shouldHaveProperJPAAnnotationsOnBoothApplicationStatusCodeField() throws NoSuchFieldException {
            // Given
            Field statusCodeField = BoothApplication.class.getDeclaredField("boothApplicationStatusCode");

            // Then
            assertThat(statusCodeField.isAnnotationPresent(ManyToOne.class)).isTrue();
            assertThat(statusCodeField.isAnnotationPresent(JoinColumn.class)).isTrue();

            ManyToOne manyToOne = statusCodeField.getAnnotation(ManyToOne.class);
            assertThat(manyToOne.fetch()).isEqualTo(FetchType.LAZY);

            JoinColumn joinColumn = statusCodeField.getAnnotation(JoinColumn.class);
            assertThat(joinColumn.name()).isEqualTo("booth_application_status_code_id");
            assertThat(joinColumn.nullable()).isFalse();
        }

        @Test
        @DisplayName("Should have correct field type for boothApplicationStatusCode")
        void shouldHaveCorrectFieldTypeForBoothApplicationStatusCode() throws NoSuchFieldException {
            // Given
            Field statusCodeField = BoothApplication.class.getDeclaredField("boothApplicationStatusCode");

            // Then
            assertThat(statusCodeField.getType()).isEqualTo(BoothApplicationStatusCode.class);
        }

        @Test
        @DisplayName("Should return null boothApplicationStatusCode initially")
        void shouldReturnNullBoothApplicationStatusCodeInitially() {
            // Given & When
            BoothApplicationStatusCode statusCode = boothApplication.getBoothApplicationStatusCode();

            // Then
            assertThat(statusCode).isNull();
        }
    }

    @Nested
    @DisplayName("Booth Payment Status Code Relationship Tests")
    class BoothPaymentStatusCodeRelationshipTests {

        @Test
        @DisplayName("Should have proper JPA annotations on boothPaymentStatusCode field")
        void shouldHaveProperJPAAnnotationsOnBoothPaymentStatusCodeField() throws NoSuchFieldException {
            // Given
            Field paymentStatusCodeField = BoothApplication.class.getDeclaredField("boothPaymentStatusCode");

            // Then
            assertThat(paymentStatusCodeField.isAnnotationPresent(ManyToOne.class)).isTrue();
            assertThat(paymentStatusCodeField.isAnnotationPresent(JoinColumn.class)).isTrue();

            ManyToOne manyToOne = paymentStatusCodeField.getAnnotation(ManyToOne.class);
            assertThat(manyToOne.fetch()).isEqualTo(FetchType.LAZY);

            JoinColumn joinColumn = paymentStatusCodeField.getAnnotation(JoinColumn.class);
            assertThat(joinColumn.name()).isEqualTo("booth_payment_status_code_id");
            assertThat(joinColumn.nullable()).isFalse();
        }

        @Test
        @DisplayName("Should have correct field type for boothPaymentStatusCode")
        void shouldHaveCorrectFieldTypeForBoothPaymentStatusCode() throws NoSuchFieldException {
            // Given
            Field paymentStatusCodeField = BoothApplication.class.getDeclaredField("boothPaymentStatusCode");

            // Then
            assertThat(paymentStatusCodeField.getType()).isEqualTo(BoothPaymentStatusCode.class);
        }

        @Test
        @DisplayName("Should return null boothPaymentStatusCode initially")
        void shouldReturnNullBoothPaymentStatusCodeInitially() {
            // Given & When
            BoothPaymentStatusCode paymentStatusCode = boothApplication.getBoothPaymentStatusCode();

            // Then
            assertThat(paymentStatusCode).isNull();
        }
    }

    @Nested
    @DisplayName("Status Updated At Field Tests")
    class StatusUpdatedAtFieldTests {

        @Test
        @DisplayName("Should have proper JPA annotations on statusUpdatedAt field")
        void shouldHaveProperJPAAnnotationsOnStatusUpdatedAtField() throws NoSuchFieldException {
            // Given
            Field statusUpdatedAtField = BoothApplication.class.getDeclaredField("statusUpdatedAt");

            // Then
            assertThat(statusUpdatedAtField.isAnnotationPresent(Column.class)).isTrue();

            Column column = statusUpdatedAtField.getAnnotation(Column.class);
            assertThat(column.name()).isEqualTo("status_updated_at");
        }

        @Test
        @DisplayName("Should have correct field type for statusUpdatedAt")
        void shouldHaveCorrectFieldTypeForStatusUpdatedAt() throws NoSuchFieldException {
            // Given
            Field statusUpdatedAtField = BoothApplication.class.getDeclaredField("statusUpdatedAt");

            // Then
            assertThat(statusUpdatedAtField.getType()).isEqualTo(LocalDateTime.class);
        }

        @Test
        @DisplayName("Should return null statusUpdatedAt initially")
        void shouldReturnNullStatusUpdatedAtInitially() {
            // Given & When
            LocalDateTime statusUpdatedAt = boothApplication.getStatusUpdatedAt();

            // Then
            assertThat(statusUpdatedAt).isNull();
        }
    }

    @Nested
    @DisplayName("Entity Equality and HashCode Tests")
    class EqualityAndHashCodeTests {

        @Test
        @DisplayName("Should handle equality correctly for entities without id")
        void shouldHandleEqualityCorrectlyForEntitiesWithoutId() {
            // Given
            BoothApplication app1 = new BoothApplication();
            BoothApplication app2 = new BoothApplication();

            // Then - Default Object equality should apply
            assertThat(app1).isNotEqualTo(app2);
            assertThat(app1.hashCode()).isNotEqualTo(app2.hashCode());
        }

        @Test
        @DisplayName("Should be equal to itself")
        void shouldBeEqualToItself() {
            // Given & When & Then
            assertThat(boothApplication).isEqualTo(boothApplication);
            assertThat(boothApplication.hashCode()).isEqualTo(boothApplication.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to null")
        void shouldNotBeEqualToNull() {
            // Given & When & Then
            assertThat(boothApplication).isNotEqualTo(null);
        }

        @Test
        @DisplayName("Should not be equal to different class")
        void shouldNotBeEqualToDifferentClass() {
            // Given
            String differentObject = "different";

            // When & Then
            assertThat(boothApplication).isNotEqualTo(differentObject);
        }
    }

    @Nested
    @DisplayName("toString Method Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string representation")
        void shouldReturnNonNullStringRepresentation() {
            // Given & When
            String toString = boothApplication.toString();

            // Then
            assertThat(toString).isNotNull();
            assertThat(toString).contains("BoothApplication");
        }
    }

    @Nested
    @DisplayName("JPA Persistence Context Tests")
    class JPAPersistenceContextTests {

        @Test
        @DisplayName("Should have entity annotation with default table name")
        void shouldHaveEntityAnnotationWithDefaultTableName() {
            // Given
            Entity entityAnnotation = BoothApplication.class.getAnnotation(Entity.class);

            // Then
            assertThat(entityAnnotation).isNotNull();
            // Default table name should be class name
            assertThat(entityAnnotation.name()).isEmpty();
        }

        @Test
        @DisplayName("Should support lazy loading for relationships")
        void shouldSupportLazyLoadingForRelationships() throws NoSuchFieldException {
            // Given
            Field eventField = BoothApplication.class.getDeclaredField("event");
            Field statusCodeField = BoothApplication.class.getDeclaredField("boothApplicationStatusCode");
            Field paymentStatusCodeField = BoothApplication.class.getDeclaredField("boothPaymentStatusCode");

            // Then
            ManyToOne eventManyToOne = eventField.getAnnotation(ManyToOne.class);
            assertThat(eventManyToOne.fetch()).isEqualTo(FetchType.LAZY);

            ManyToOne statusCodeManyToOne = statusCodeField.getAnnotation(ManyToOne.class);
            assertThat(statusCodeManyToOne.fetch()).isEqualTo(FetchType.LAZY);

            ManyToOne paymentStatusCodeManyToOne = paymentStatusCodeField.getAnnotation(ManyToOne.class);
            assertThat(paymentStatusCodeManyToOne.fetch()).isEqualTo(FetchType.LAZY);
        }
    }

    @Nested
    @DisplayName("Column Constraint Tests")
    class ColumnConstraintTests {

        @ParameterizedTest
        @ValueSource(strings = {"boothEmail", "managerName", "email", "contactNumber", "officialUrl"})
        @DisplayName("Should have non-nullable constraint on required string fields")
        void shouldHaveNonNullableConstraintOnRequiredStringFields(String fieldName) throws NoSuchFieldException {
            // Given
            Field field = BoothApplication.class.getDeclaredField(fieldName);

            // Then
            assertThat(field.isAnnotationPresent(Column.class)).isTrue();
            Column column = field.getAnnotation(Column.class);
            assertThat(column.nullable()).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"event", "boothApplicationStatusCode", "boothPaymentStatusCode"})
        @DisplayName("Should have non-nullable constraint on required relationship fields")
        void shouldHaveNonNullableConstraintOnRequiredRelationshipFields(String fieldName) throws NoSuchFieldException {
            // Given
            Field field = BoothApplication.class.getDeclaredField(fieldName);

            // Then
            assertThat(field.isAnnotationPresent(JoinColumn.class)).isTrue();
            JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
            assertThat(joinColumn.nullable()).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"boothDescription", "adminComment"})
        @DisplayName("Should allow nullable values for optional text fields")
        void shouldAllowNullableValuesForOptionalTextFields(String fieldName) throws NoSuchFieldException {
            // Given
            Field field = BoothApplication.class.getDeclaredField(fieldName);

            // When & Then
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                // These fields should either not specify nullable (default true) or explicitly allow null
                assertThat(column.nullable()).isTrue();
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"applyAt", "statusUpdatedAt"})
        @DisplayName("Should allow nullable values for optional datetime fields")
        void shouldAllowNullableValuesForOptionalDateTimeFields(String fieldName) throws NoSuchFieldException {
            // Given
            Field field = BoothApplication.class.getDeclaredField(fieldName);

            // When & Then
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                // These fields should either not specify nullable (default true) or explicitly allow null
                assertThat(column.nullable()).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("Field Length Constraint Tests")
    class FieldLengthConstraintTests {

        @Test
        @DisplayName("Should have correct length constraint for boothEmail")
        void shouldHaveCorrectLengthConstraintForBoothEmail() throws NoSuchFieldException {
            // Given
            Field boothEmailField = BoothApplication.class.getDeclaredField("boothEmail");

            // When & Then
            Column column = boothEmailField.getAnnotation(Column.class);
            assertThat(column.length()).isEqualTo(100);
        }

        @Test
        @DisplayName("Should have correct length constraint for managerName")
        void shouldHaveCorrectLengthConstraintForManagerName() throws NoSuchFieldException {
            // Given
            Field managerNameField = BoothApplication.class.getDeclaredField("managerName");

            // When & Then
            Column column = managerNameField.getAnnotation(Column.class);
            assertThat(column.length()).isEqualTo(20);
        }

        @Test
        @DisplayName("Should have correct length constraint for email")
        void shouldHaveCorrectLengthConstraintForEmail() throws NoSuchFieldException {
            // Given
            Field emailField = BoothApplication.class.getDeclaredField("email");

            // When & Then
            Column column = emailField.getAnnotation(Column.class);
            assertThat(column.length()).isEqualTo(100);
        }

        @Test
        @DisplayName("Should have correct length constraint for contactNumber")
        void shouldHaveCorrectLengthConstraintForContactNumber() throws NoSuchFieldException {
            // Given
            Field contactNumberField = BoothApplication.class.getDeclaredField("contactNumber");

            // When & Then
            Column column = contactNumberField.getAnnotation(Column.class);
            assertThat(column.length()).isEqualTo(20);
        }

        @Test
        @DisplayName("Should have correct length constraint for officialUrl")
        void shouldHaveCorrectLengthConstraintForOfficialUrl() throws NoSuchFieldException {
            // Given
            Field officialUrlField = BoothApplication.class.getDeclaredField("officialUrl");

            // When & Then
            Column column = officialUrlField.getAnnotation(Column.class);
            assertThat(column.length()).isEqualTo(512);
        }
    }

    @Nested
    @DisplayName("Column Definition Tests")
    class ColumnDefinitionTests {

        @Test
        @DisplayName("Should have TEXT column definition for boothDescription")
        void shouldHaveTextColumnDefinitionForBoothDescription() throws NoSuchFieldException {
            // Given
            Field boothDescriptionField = BoothApplication.class.getDeclaredField("boothDescription");

            // When & Then
            Column column = boothDescriptionField.getAnnotation(Column.class);
            assertThat(column.columnDefinition()).isEqualTo("TEXT");
        }

        @Test
        @DisplayName("Should have TEXT column definition for adminComment")
        void shouldHaveTextColumnDefinitionForAdminComment() throws NoSuchFieldException {
            // Given
            Field adminCommentField = BoothApplication.class.getDeclaredField("adminComment");

            // When & Then
            Column column = adminCommentField.getAnnotation(Column.class);
            assertThat(column.columnDefinition()).isEqualTo("TEXT");
        }

        @Test
        @DisplayName("Should have DATETIME DEFAULT CURRENT_TIMESTAMP for applyAt field")
        void shouldHaveDatetimeDefaultCurrentTimestampForApplyAtField() throws NoSuchFieldException {
            // Given
            Field applyAtField = BoothApplication.class.getDeclaredField("applyAt");

            // When & Then
            Column column = applyAtField.getAnnotation(Column.class);
            assertThat(column.columnDefinition()).isEqualTo("DATETIME DEFAULT CURRENT_TIMESTAMP");
        }
    }

    @Nested
    @DisplayName("Column Name Mapping Tests")
    class ColumnNameMappingTests {

        @Test
        @DisplayName("Should have correct column name mapping for database fields")
        void shouldHaveCorrectColumnNameMappingForDatabaseFields() throws NoSuchFieldException {
            // Given & When & Then
            assertThat(BoothApplication.class.getDeclaredField("id").getAnnotation(Column.class).name())
                    .isEqualTo("booth_application_id");
            
            assertThat(BoothApplication.class.getDeclaredField("boothEmail").getAnnotation(Column.class).name())
                    .isEqualTo("booth_email");
            
            assertThat(BoothApplication.class.getDeclaredField("boothDescription").getAnnotation(Column.class).name())
                    .isEqualTo("booth_description");
            
            assertThat(BoothApplication.class.getDeclaredField("managerName").getAnnotation(Column.class).name())
                    .isEqualTo("manager_name");
            
            assertThat(BoothApplication.class.getDeclaredField("contactNumber").getAnnotation(Column.class).name())
                    .isEqualTo("contact_number");
            
            assertThat(BoothApplication.class.getDeclaredField("officialUrl").getAnnotation(Column.class).name())
                    .isEqualTo("official_url");
            
            assertThat(BoothApplication.class.getDeclaredField("applyAt").getAnnotation(Column.class).name())
                    .isEqualTo("apply_at");
            
            assertThat(BoothApplication.class.getDeclaredField("adminComment").getAnnotation(Column.class).name())
                    .isEqualTo("admin_comment");
            
            assertThat(BoothApplication.class.getDeclaredField("statusUpdatedAt").getAnnotation(Column.class).name())
                    .isEqualTo("status_updated_at");
        }

        @Test
        @DisplayName("Should have correct join column name mapping for relationship fields")
        void shouldHaveCorrectJoinColumnNameMappingForRelationshipFields() throws NoSuchFieldException {
            // Given & When & Then
            assertThat(BoothApplication.class.getDeclaredField("event").getAnnotation(JoinColumn.class).name())
                    .isEqualTo("event_id");
            
            assertThat(BoothApplication.class.getDeclaredField("boothApplicationStatusCode").getAnnotation(JoinColumn.class).name())
                    .isEqualTo("booth_application_status_code_id");
            
            assertThat(BoothApplication.class.getDeclaredField("boothPaymentStatusCode").getAnnotation(JoinColumn.class).name())
                    .isEqualTo("booth_payment_status_code_id");
        }
    }

    @Nested
    @DisplayName("Field Accessibility Tests")
    class FieldAccessibilityTests {

        @Test
        @DisplayName("Should have all fields as private")
        void shouldHaveAllFieldsAsPrivate() throws NoSuchFieldException {
            // Given
            String[] fieldNames = {"id", "event", "boothEmail", "boothDescription", "managerName", 
                                 "email", "contactNumber", "officialUrl", "applyAt", "adminComment", 
                                 "boothApplicationStatusCode", "boothPaymentStatusCode", "statusUpdatedAt"};

            // When & Then
            for (String fieldName : fieldNames) {
                Field field = BoothApplication.class.getDeclaredField(fieldName);
                assertThat(java.lang.reflect.Modifier.isPrivate(field.getModifiers())).isTrue();
            }
        }

        @Test
        @DisplayName("Should have getter methods for all fields")
        void shouldHaveGetterMethodsForAllFields() throws NoSuchMethodException {
            // Given
            Class<BoothApplication> clazz = BoothApplication.class;

            // When & Then
            assertThat(clazz.getMethod("getId")).isNotNull();
            assertThat(clazz.getMethod("getEvent")).isNotNull();
            assertThat(clazz.getMethod("getBoothEmail")).isNotNull();
            assertThat(clazz.getMethod("getBoothDescription")).isNotNull();
            assertThat(clazz.getMethod("getManagerName")).isNotNull();
            assertThat(clazz.getMethod("getEmail")).isNotNull();
            assertThat(clazz.getMethod("getContactNumber")).isNotNull();
            assertThat(clazz.getMethod("getOfficialUrl")).isNotNull();
            assertThat(clazz.getMethod("getApplyAt")).isNotNull();
            assertThat(clazz.getMethod("getAdminComment")).isNotNull();
            assertThat(clazz.getMethod("getBoothApplicationStatusCode")).isNotNull();
            assertThat(clazz.getMethod("getBoothPaymentStatusCode")).isNotNull();
            assertThat(clazz.getMethod("getStatusUpdatedAt")).isNotNull();
        }
    }
}
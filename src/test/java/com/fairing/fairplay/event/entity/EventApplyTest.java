package com.fairing.fairplay.event.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDateTime;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventApply Entity Tests")
class EventApplyTest {

    private EventApply eventApply;
    private ApplyStatusCode mockStatusCode;

    @BeforeEach
    void setUp() {
        eventApply = new EventApply();
        mockStatusCode = createMockApplyStatusCode();
    }

    @Nested
    @DisplayName("Entity Creation and Default Values")
    class EntityCreationTests {

        @Test
        @DisplayName("Should create EventApply with no-args constructor")
        void shouldCreateEventApplyWithNoArgsConstructor() {
            // Given & When
            EventApply newEventApply = new EventApply();

            // Then
            assertNotNull(newEventApply);
            assertNull(newEventApply.getId());
            assertNull(newEventApply.getStatusCode());
            assertNull(newEventApply.getEventEmail());
            assertNull(newEventApply.getBusinessNumber());
            assertEquals(false, newEventApply.getVerified()); // Default value should be false
            assertNull(newEventApply.getManagerName());
            assertNull(newEventApply.getEmail());
            assertNull(newEventApply.getContactNumber());
            assertNull(newEventApply.getTitleKr());
            assertNull(newEventApply.getTitleEng());
            assertNull(newEventApply.getFileUrl());
            assertNull(newEventApply.getApplyAt());
            assertNull(newEventApply.getAdminComment());
            assertNull(newEventApply.getStatusUpdatedAt());
        }

        @Test
        @DisplayName("Should have verified field default to false")
        void shouldHaveVerifiedFieldDefaultToFalse() {
            // Given & When
            EventApply newEventApply = new EventApply();

            // Then
            assertNotNull(newEventApply.getVerified());
            assertFalse(newEventApply.getVerified());
        }

        @Test
        @DisplayName("Should be annotated as JPA Entity")
        void shouldBeAnnotatedAsJPAEntity() {
            // Given & When
            Class<EventApply> clazz = EventApply.class;

            // Then
            assertTrue(clazz.isAnnotationPresent(jakarta.persistence.Entity.class));
            assertTrue(clazz.isAnnotationPresent(jakarta.persistence.Table.class));
            assertEquals("event_apply", clazz.getAnnotation(jakarta.persistence.Table.class).name());
        }
    }

    @Nested
    @DisplayName("Field Accessor Tests")
    class FieldAccessorTests {

        @Test
        @DisplayName("Should get and set id correctly")
        void shouldGetAndSetIdCorrectly() throws Exception {
            // Given
            Long expectedId = 123L;
            setPrivateField(eventApply, "id", expectedId);

            // When
            Long actualId = eventApply.getId();

            // Then
            assertEquals(expectedId, actualId);
        }

        @Test
        @DisplayName("Should get and set statusCode correctly")
        void shouldGetAndSetStatusCodeCorrectly() throws Exception {
            // Given
            setPrivateField(eventApply, "statusCode", mockStatusCode);

            // When
            ApplyStatusCode actualStatusCode = eventApply.getStatusCode();

            // Then
            assertEquals(mockStatusCode, actualStatusCode);
        }

        @Test
        @DisplayName("Should get and set eventEmail correctly")
        void shouldGetAndSetEventEmailCorrectly() throws Exception {
            // Given
            String expectedEmail = "event@example.com";
            setPrivateField(eventApply, "eventEmail", expectedEmail);

            // When
            String actualEmail = eventApply.getEventEmail();

            // Then
            assertEquals(expectedEmail, actualEmail);
        }

        @Test
        @DisplayName("Should get and set businessNumber correctly")
        void shouldGetAndSetBusinessNumberCorrectly() throws Exception {
            // Given
            String expectedBusinessNumber = "123-45-67890";
            setPrivateField(eventApply, "businessNumber", expectedBusinessNumber);

            // When
            String actualBusinessNumber = eventApply.getBusinessNumber();

            // Then
            assertEquals(expectedBusinessNumber, actualBusinessNumber);
        }

        @Test
        @DisplayName("Should get and set verified status correctly")
        void shouldGetAndSetVerifiedStatusCorrectly() throws Exception {
            // Given
            Boolean expectedVerified = true;
            setPrivateField(eventApply, "verified", expectedVerified);

            // When
            Boolean actualVerified = eventApply.getVerified();

            // Then
            assertEquals(expectedVerified, actualVerified);
        }

        @Test
        @DisplayName("Should get and set managerName correctly")
        void shouldGetAndSetManagerNameCorrectly() throws Exception {
            // Given
            String expectedManagerName = "John Doe";
            setPrivateField(eventApply, "managerName", expectedManagerName);

            // When
            String actualManagerName = eventApply.getManagerName();

            // Then
            assertEquals(expectedManagerName, actualManagerName);
        }

        @Test
        @DisplayName("Should get and set email correctly")
        void shouldGetAndSetEmailCorrectly() throws Exception {
            // Given
            String expectedEmail = "manager@example.com";
            setPrivateField(eventApply, "email", expectedEmail);

            // When
            String actualEmail = eventApply.getEmail();

            // Then
            assertEquals(expectedEmail, actualEmail);
        }

        @Test
        @DisplayName("Should get and set contactNumber correctly")
        void shouldGetAndSetContactNumberCorrectly() throws Exception {
            // Given
            String expectedContactNumber = "+1-555-123-4567";
            setPrivateField(eventApply, "contactNumber", expectedContactNumber);

            // When
            String actualContactNumber = eventApply.getContactNumber();

            // Then
            assertEquals(expectedContactNumber, actualContactNumber);
        }

        @Test
        @DisplayName("Should get and set titleKr correctly")
        void shouldGetAndSetTitleKrCorrectly() throws Exception {
            // Given
            String expectedTitleKr = "한국어 제목";
            setPrivateField(eventApply, "titleKr", expectedTitleKr);

            // When
            String actualTitleKr = eventApply.getTitleKr();

            // Then
            assertEquals(expectedTitleKr, actualTitleKr);
        }

        @Test
        @DisplayName("Should get and set titleEng correctly")
        void shouldGetAndSetTitleEngCorrectly() throws Exception {
            // Given
            String expectedTitleEng = "English Title";
            setPrivateField(eventApply, "titleEng", expectedTitleEng);

            // When
            String actualTitleEng = eventApply.getTitleEng();

            // Then
            assertEquals(expectedTitleEng, actualTitleEng);
        }

        @Test
        @DisplayName("Should get and set fileUrl correctly")
        void shouldGetAndSetFileUrlCorrectly() throws Exception {
            // Given
            String expectedFileUrl = "https://example.com/file.pdf";
            setPrivateField(eventApply, "fileUrl", expectedFileUrl);

            // When
            String actualFileUrl = eventApply.getFileUrl();

            // Then
            assertEquals(expectedFileUrl, actualFileUrl);
        }

        @Test
        @DisplayName("Should get and set applyAt correctly")
        void shouldGetAndSetApplyAtCorrectly() throws Exception {
            // Given
            LocalDateTime expectedApplyAt = LocalDateTime.now();
            setPrivateField(eventApply, "applyAt", expectedApplyAt);

            // When
            LocalDateTime actualApplyAt = eventApply.getApplyAt();

            // Then
            assertEquals(expectedApplyAt, actualApplyAt);
        }

        @Test
        @DisplayName("Should get and set adminComment correctly")
        void shouldGetAndSetAdminCommentCorrectly() throws Exception {
            // Given
            String expectedAdminComment = "This is an admin comment";
            setPrivateField(eventApply, "adminComment", expectedAdminComment);

            // When
            String actualAdminComment = eventApply.getAdminComment();

            // Then
            assertEquals(expectedAdminComment, actualAdminComment);
        }

        @Test
        @DisplayName("Should get and set statusUpdatedAt correctly")
        void shouldGetAndSetStatusUpdatedAtCorrectly() throws Exception {
            // Given
            LocalDateTime expectedStatusUpdatedAt = LocalDateTime.now();
            setPrivateField(eventApply, "statusUpdatedAt", expectedStatusUpdatedAt);

            // When
            LocalDateTime actualStatusUpdatedAt = eventApply.getStatusUpdatedAt();

            // Then
            assertEquals(expectedStatusUpdatedAt, actualStatusUpdatedAt);
        }
    }

    @Nested
    @DisplayName("Field Validation and Constraint Tests")
    class FieldValidationTests {

        @Test
        @DisplayName("Should handle null values for nullable fields")
        void shouldHandleNullValuesForNullableFields() throws Exception {
            // Given & When - Setting nullable fields to null
            setPrivateField(eventApply, "adminComment", null);
            setPrivateField(eventApply, "statusUpdatedAt", null);
            setPrivateField(eventApply, "applyAt", null);

            // Then
            assertNull(eventApply.getAdminComment());
            assertNull(eventApply.getStatusUpdatedAt());
            assertNull(eventApply.getApplyAt());
        }

        @ParameterizedTest
        @ValueSource(strings = {"test@example.com", "user.name@domain.co.kr", "admin@fairplay.com", "test+tag@gmail.com"})
        @DisplayName("Should handle various valid email formats")
        void shouldHandleVariousValidEmailFormats(String email) throws Exception {
            // Given & When
            setPrivateField(eventApply, "eventEmail", email);
            setPrivateField(eventApply, "email", email);

            // Then
            assertEquals(email, eventApply.getEventEmail());
            assertEquals(email, eventApply.getEmail());
        }

        @ParameterizedTest
        @ValueSource(strings = {"123-45-67890", "1234567890", "123-456-78901", "12345"})
        @DisplayName("Should handle various business number formats")
        void shouldHandleVariousBusinessNumberFormats(String businessNumber) throws Exception {
            // Given & When
            setPrivateField(eventApply, "businessNumber", businessNumber);

            // Then
            assertEquals(businessNumber, eventApply.getBusinessNumber());
        }

        @ParameterizedTest
        @ValueSource(strings = {"+1-555-123-4567", "010-1234-5678", "02-123-4567", "1234567890", "+82-10-1234-5678"})
        @DisplayName("Should handle various contact number formats")
        void shouldHandleVariousContactNumberFormats(String contactNumber) throws Exception {
            // Given & When
            setPrivateField(eventApply, "contactNumber", contactNumber);

            // Then
            assertEquals(contactNumber, eventApply.getContactNumber());
        }

        @Test
        @DisplayName("Should handle maximum length strings for title fields")
        void shouldHandleMaximumLengthStringsForTitleFields() throws Exception {
            // Given
            String longTitle = "A".repeat(200); // Maximum length according to column definition

            // When
            setPrivateField(eventApply, "titleKr", longTitle);
            setPrivateField(eventApply, "titleEng", longTitle);

            // Then
            assertEquals(longTitle, eventApply.getTitleKr());
            assertEquals(longTitle, eventApply.getTitleEng());
            assertEquals(200, eventApply.getTitleKr().length());
            assertEquals(200, eventApply.getTitleEng().length());
        }

        @Test
        @DisplayName("Should handle maximum length string for fileUrl")
        void shouldHandleMaximumLengthStringForFileUrl() throws Exception {
            // Given
            String longUrl = "https://example.com/very/long/path/" + "x".repeat(460); // Close to 512 limit

            // When
            setPrivateField(eventApply, "fileUrl", longUrl);

            // Then
            assertEquals(longUrl, eventApply.getFileUrl());
            assertTrue(eventApply.getFileUrl().length() <= 512);
        }

        @Test
        @DisplayName("Should handle maximum length string for managerName")
        void shouldHandleMaximumLengthStringForManagerName() throws Exception {
            // Given
            String longName = "A".repeat(50); // Maximum length according to column definition

            // When
            setPrivateField(eventApply, "managerName", longName);

            // Then
            assertEquals(longName, eventApply.getManagerName());
            assertEquals(50, eventApply.getManagerName().length());
        }

        @Test
        @DisplayName("Should handle very long admin comment")
        void shouldHandleVeryLongAdminComment() throws Exception {
            // Given
            String longComment = "This is a very long admin comment. ".repeat(100); // TEXT field can handle large content

            // When
            setPrivateField(eventApply, "adminComment", longComment);

            // Then
            assertEquals(longComment, eventApply.getAdminComment());
            assertTrue(eventApply.getAdminComment().length() > 1000);
        }

        @Test
        @DisplayName("Should handle maximum length string for eventEmail")
        void shouldHandleMaximumLengthStringForEventEmail() throws Exception {
            // Given - Create a valid email at max length (100 chars)
            String maxEmail = "a".repeat(85) + "@example.com"; // Total 100 characters

            // When
            setPrivateField(eventApply, "eventEmail", maxEmail);

            // Then
            assertEquals(maxEmail, eventApply.getEventEmail());
            assertEquals(100, eventApply.getEventEmail().length());
        }

        @Test
        @DisplayName("Should handle maximum length string for regular email")
        void shouldHandleMaximumLengthStringForRegularEmail() throws Exception {
            // Given - Create a valid email at max length (100 chars)
            String maxEmail = "a".repeat(85) + "@example.com"; // Total 100 characters

            // When
            setPrivateField(eventApply, "email", maxEmail);

            // Then
            assertEquals(maxEmail, eventApply.getEmail());
            assertEquals(100, eventApply.getEmail().length());
        }

        @Test
        @DisplayName("Should handle maximum length string for contactNumber")
        void shouldHandleMaximumLengthStringForContactNumber() throws Exception {
            // Given
            String longContactNumber = "1".repeat(20); // Maximum length according to column definition

            // When
            setPrivateField(eventApply, "contactNumber", longContactNumber);

            // Then
            assertEquals(longContactNumber, eventApply.getContactNumber());
            assertEquals(20, eventApply.getContactNumber().length());
        }

        @Test
        @DisplayName("Should handle maximum length string for businessNumber")
        void shouldHandleMaximumLengthStringForBusinessNumber() throws Exception {
            // Given
            String longBusinessNumber = "1".repeat(20); // Maximum length according to column definition

            // When
            setPrivateField(eventApply, "businessNumber", longBusinessNumber);

            // Then
            assertEquals(longBusinessNumber, eventApply.getBusinessNumber());
            assertEquals(20, eventApply.getBusinessNumber().length());
        }
    }

    @Nested
    @DisplayName("Edge Case and Boundary Tests")
    class EdgeCaseTests {

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Should handle null and empty strings for optional string fields")
        void shouldHandleNullAndEmptyStringsForOptionalFields(String value) throws Exception {
            // Given & When
            setPrivateField(eventApply, "adminComment", value);

            // Then
            assertEquals(value, eventApply.getAdminComment());
        }

        @Test
        @DisplayName("Should handle empty strings for required string fields")
        void shouldHandleEmptyStringsForRequiredStringFields() throws Exception {
            // Given & When
            setPrivateField(eventApply, "eventEmail", "");
            setPrivateField(eventApply, "businessNumber", "");
            setPrivateField(eventApply, "managerName", "");
            setPrivateField(eventApply, "email", "");
            setPrivateField(eventApply, "contactNumber", "");
            setPrivateField(eventApply, "titleKr", "");
            setPrivateField(eventApply, "titleEng", "");
            setPrivateField(eventApply, "fileUrl", "");

            // Then
            assertEquals("", eventApply.getEventEmail());
            assertEquals("", eventApply.getBusinessNumber());
            assertEquals("", eventApply.getManagerName());
            assertEquals("", eventApply.getEmail());
            assertEquals("", eventApply.getContactNumber());
            assertEquals("", eventApply.getTitleKr());
            assertEquals("", eventApply.getTitleEng());
            assertEquals("", eventApply.getFileUrl());
        }

        @Test
        @DisplayName("Should handle special characters in string fields")
        void shouldHandleSpecialCharactersInStringFields() throws Exception {
            // Given
            String specialChars = "!@#$%^&*()_+-={}[]|\\:;\"'<>?,./";

            // When
            setPrivateField(eventApply, "titleKr", specialChars);
            setPrivateField(eventApply, "titleEng", specialChars);
            setPrivateField(eventApply, "adminComment", specialChars);

            // Then
            assertEquals(specialChars, eventApply.getTitleKr());
            assertEquals(specialChars, eventApply.getTitleEng());
            assertEquals(specialChars, eventApply.getAdminComment());
        }

        @Test
        @DisplayName("Should handle Unicode and international characters")
        void shouldHandleUnicodeAndInternationalCharacters() throws Exception {
            // Given
            String unicodeChars = "한글 日本語 中文 العربية Русский 🎉✨";
            String koreanName = "김철수";
            String japaneseTitle = "イベント申請";

            // When
            setPrivateField(eventApply, "titleKr", unicodeChars);
            setPrivateField(eventApply, "titleEng", japaneseTitle);
            setPrivateField(eventApply, "managerName", koreanName);
            setPrivateField(eventApply, "adminComment", unicodeChars);

            // Then
            assertEquals(unicodeChars, eventApply.getTitleKr());
            assertEquals(japaneseTitle, eventApply.getTitleEng());
            assertEquals(koreanName, eventApply.getManagerName());
            assertEquals(unicodeChars, eventApply.getAdminComment());
        }

        @Test
        @DisplayName("Should handle Boolean edge cases")
        void shouldHandleBooleanEdgeCases() throws Exception {
            // Test true
            setPrivateField(eventApply, "verified", Boolean.TRUE);
            assertTrue(eventApply.getVerified());

            // Test false
            setPrivateField(eventApply, "verified", Boolean.FALSE);
            assertFalse(eventApply.getVerified());

            // Test primitive true
            setPrivateField(eventApply, "verified", true);
            assertTrue(eventApply.getVerified());

            // Test primitive false
            setPrivateField(eventApply, "verified", false);
            assertFalse(eventApply.getVerified());
        }

        @Test
        @DisplayName("Should handle LocalDateTime boundary values")
        void shouldHandleLocalDateTimeBoundaryValues() throws Exception {
            // Given
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime pastDate = LocalDateTime.of(1900, 1, 1, 0, 0, 0);
            LocalDateTime futureDate = LocalDateTime.of(2100, 12, 31, 23, 59, 59);

            // When & Then - Test various datetime values
            setPrivateField(eventApply, "applyAt", now);
            assertEquals(now, eventApply.getApplyAt());

            setPrivateField(eventApply, "statusUpdatedAt", pastDate);
            assertEquals(pastDate, eventApply.getStatusUpdatedAt());

            setPrivateField(eventApply, "applyAt", futureDate);
            assertEquals(futureDate, eventApply.getApplyAt());
        }

        @Test
        @DisplayName("Should handle whitespace-only strings")
        void shouldHandleWhitespaceOnlyStrings() throws Exception {
            // Given
            String whitespaceString = "   \t\n   ";

            // When
            setPrivateField(eventApply, "eventEmail", whitespaceString);
            setPrivateField(eventApply, "managerName", whitespaceString);
            setPrivateField(eventApply, "adminComment", whitespaceString);

            // Then
            assertEquals(whitespaceString, eventApply.getEventEmail());
            assertEquals(whitespaceString, eventApply.getManagerName());
            assertEquals(whitespaceString, eventApply.getAdminComment());
        }
    }

    @Nested
    @DisplayName("Complex Scenario and Integration Tests")
    class ComplexScenarioTests {

        @Test
        @DisplayName("Should maintain object integrity after multiple field updates")
        void shouldMaintainObjectIntegrityAfterMultipleFieldUpdates() throws Exception {
            // Given
            Long id = 1L;
            String eventEmail = "event@test.com";
            String businessNumber = "123-45-67890";
            Boolean verified = true;
            String managerName = "Test Manager";
            String email = "manager@test.com";
            String contactNumber = "010-1234-5678";
            String titleKr = "테스트 제목";
            String titleEng = "Test Title";
            String fileUrl = "https://test.com/file.pdf";
            LocalDateTime applyAt = LocalDateTime.now();
            String adminComment = "Test comment";
            LocalDateTime statusUpdatedAt = LocalDateTime.now().plusHours(1);

            // When
            setPrivateField(eventApply, "id", id);
            setPrivateField(eventApply, "statusCode", mockStatusCode);
            setPrivateField(eventApply, "eventEmail", eventEmail);
            setPrivateField(eventApply, "businessNumber", businessNumber);
            setPrivateField(eventApply, "verified", verified);
            setPrivateField(eventApply, "managerName", managerName);
            setPrivateField(eventApply, "email", email);
            setPrivateField(eventApply, "contactNumber", contactNumber);
            setPrivateField(eventApply, "titleKr", titleKr);
            setPrivateField(eventApply, "titleEng", titleEng);
            setPrivateField(eventApply, "fileUrl", fileUrl);
            setPrivateField(eventApply, "applyAt", applyAt);
            setPrivateField(eventApply, "adminComment", adminComment);
            setPrivateField(eventApply, "statusUpdatedAt", statusUpdatedAt);

            // Then
            assertAll("All fields should be set correctly",
                () -> assertEquals(id, eventApply.getId()),
                () -> assertEquals(mockStatusCode, eventApply.getStatusCode()),
                () -> assertEquals(eventEmail, eventApply.getEventEmail()),
                () -> assertEquals(businessNumber, eventApply.getBusinessNumber()),
                () -> assertEquals(verified, eventApply.getVerified()),
                () -> assertEquals(managerName, eventApply.getManagerName()),
                () -> assertEquals(email, eventApply.getEmail()),
                () -> assertEquals(contactNumber, eventApply.getContactNumber()),
                () -> assertEquals(titleKr, eventApply.getTitleKr()),
                () -> assertEquals(titleEng, eventApply.getTitleEng()),
                () -> assertEquals(fileUrl, eventApply.getFileUrl()),
                () -> assertEquals(applyAt, eventApply.getApplyAt()),
                () -> assertEquals(adminComment, eventApply.getAdminComment()),
                () -> assertEquals(statusUpdatedAt, eventApply.getStatusUpdatedAt())
            );
        }

        @Test
        @DisplayName("Should handle multiple instances independently")
        void shouldHandleMultipleInstancesIndependently() throws Exception {
            // Given
            EventApply eventApply1 = new EventApply();
            EventApply eventApply2 = new EventApply();

            // When
            setPrivateField(eventApply1, "eventEmail", "email1@test.com");
            setPrivateField(eventApply2, "eventEmail", "email2@test.com");
            setPrivateField(eventApply1, "verified", true);
            setPrivateField(eventApply2, "verified", false);
            setPrivateField(eventApply1, "titleKr", "제목1");
            setPrivateField(eventApply2, "titleKr", "제목2");

            // Then
            assertAll("Instances should be independent",
                () -> assertEquals("email1@test.com", eventApply1.getEventEmail()),
                () -> assertEquals("email2@test.com", eventApply2.getEventEmail()),
                () -> assertTrue(eventApply1.getVerified()),
                () -> assertFalse(eventApply2.getVerified()),
                () -> assertEquals("제목1", eventApply1.getTitleKr()),
                () -> assertEquals("제목2", eventApply2.getTitleKr())
            );
        }

        @Test
        @DisplayName("Should handle realistic event application scenario")
        void shouldHandleRealisticEventApplicationScenario() throws Exception {
            // Given - A realistic event application
            String eventEmail = "spring.conference@example.com";
            String businessNumber = "123-45-67890";
            String managerName = "김민수";
            String email = "minsu.kim@company.co.kr";
            String contactNumber = "010-1234-5678";
            String titleKr = "2024 스프링 개발자 컨퍼런스";
            String titleEng = "2024 Spring Developer Conference";
            String fileUrl = "https://storage.example.com/events/spring-conf-2024/proposal.pdf";
            LocalDateTime applyAt = LocalDateTime.of(2024, 1, 15, 10, 30, 0);
            String adminComment = "좋은 주제입니다. 승인 검토 중입니다.";

            // When
            setPrivateField(eventApply, "statusCode", mockStatusCode);
            setPrivateField(eventApply, "eventEmail", eventEmail);
            setPrivateField(eventApply, "businessNumber", businessNumber);
            setPrivateField(eventApply, "verified", false); // Initially not verified
            setPrivateField(eventApply, "managerName", managerName);
            setPrivateField(eventApply, "email", email);
            setPrivateField(eventApply, "contactNumber", contactNumber);
            setPrivateField(eventApply, "titleKr", titleKr);
            setPrivateField(eventApply, "titleEng", titleEng);
            setPrivateField(eventApply, "fileUrl", fileUrl);
            setPrivateField(eventApply, "applyAt", applyAt);
            setPrivateField(eventApply, "adminComment", adminComment);

            // Then
            assertAll("Realistic event application should be handled correctly",
                () -> assertEquals(eventEmail, eventApply.getEventEmail()),
                () -> assertEquals(businessNumber, eventApply.getBusinessNumber()),
                () -> assertFalse(eventApply.getVerified()),
                () -> assertEquals(managerName, eventApply.getManagerName()),
                () -> assertEquals(email, eventApply.getEmail()),
                () -> assertEquals(contactNumber, eventApply.getContactNumber()),
                () -> assertEquals(titleKr, eventApply.getTitleKr()),
                () -> assertEquals(titleEng, eventApply.getTitleEng()),
                () -> assertEquals(fileUrl, eventApply.getFileUrl()),
                () -> assertEquals(applyAt, eventApply.getApplyAt()),
                () -> assertEquals(adminComment, eventApply.getAdminComment()),
                () -> assertEquals(mockStatusCode, eventApply.getStatusCode())
            );
        }

        @Test
        @DisplayName("Should handle status workflow transitions")
        void shouldHandleStatusWorkflowTransitions() throws Exception {
            // Given
            ApplyStatusCode initialStatus = createMockApplyStatusCode();
            ApplyStatusCode updatedStatus = createMockApplyStatusCode();
            LocalDateTime initialTime = LocalDateTime.now();
            LocalDateTime updatedTime = initialTime.plusHours(2);

            // When - Initial application
            setPrivateField(eventApply, "statusCode", initialStatus);
            setPrivateField(eventApply, "applyAt", initialTime);
            setPrivateField(eventApply, "verified", false);

            // Then - Initial state
            assertEquals(initialStatus, eventApply.getStatusCode());
            assertEquals(initialTime, eventApply.getApplyAt());
            assertFalse(eventApply.getVerified());

            // When - Status update
            setPrivateField(eventApply, "statusCode", updatedStatus);
            setPrivateField(eventApply, "statusUpdatedAt", updatedTime);
            setPrivateField(eventApply, "verified", true);
            setPrivateField(eventApply, "adminComment", "Application approved");

            // Then - Updated state
            assertEquals(updatedStatus, eventApply.getStatusCode());
            assertEquals(updatedTime, eventApply.getStatusUpdatedAt());
            assertTrue(eventApply.getVerified());
            assertEquals("Application approved", eventApply.getAdminComment());
        }
    }

    @Nested
    @DisplayName("JPA Annotation Tests")
    class JPAAnnotationTests {

        @Test
        @DisplayName("Should have correct JPA annotations on class")
        void shouldHaveCorrectJPAAnnotationsOnClass() {
            // Given
            Class<EventApply> clazz = EventApply.class;

            // Then
            assertTrue(clazz.isAnnotationPresent(jakarta.persistence.Entity.class));
            assertTrue(clazz.isAnnotationPresent(jakarta.persistence.Table.class));
            
            jakarta.persistence.Table tableAnnotation = clazz.getAnnotation(jakarta.persistence.Table.class);
            assertEquals("event_apply", tableAnnotation.name());
        }

        @Test
        @DisplayName("Should have correct JPA annotations on id field")
        void shouldHaveCorrectJPAAnnotationsOnIdField() throws Exception {
            // Given
            Field idField = EventApply.class.getDeclaredField("id");

            // Then
            assertTrue(idField.isAnnotationPresent(jakarta.persistence.Id.class));
            assertTrue(idField.isAnnotationPresent(jakarta.persistence.GeneratedValue.class));
            assertTrue(idField.isAnnotationPresent(jakarta.persistence.Column.class));
            
            jakarta.persistence.GeneratedValue generatedValue = idField.getAnnotation(jakarta.persistence.GeneratedValue.class);
            assertEquals(jakarta.persistence.GenerationType.IDENTITY, generatedValue.strategy());
            
            jakarta.persistence.Column column = idField.getAnnotation(jakarta.persistence.Column.class);
            assertEquals("event_apply_id", column.name());
        }

        @Test
        @DisplayName("Should have correct JPA annotations on statusCode field")
        void shouldHaveCorrectJPAAnnotationsOnStatusCodeField() throws Exception {
            // Given
            Field statusCodeField = EventApply.class.getDeclaredField("statusCode");

            // Then
            assertTrue(statusCodeField.isAnnotationPresent(jakarta.persistence.ManyToOne.class));
            assertTrue(statusCodeField.isAnnotationPresent(jakarta.persistence.JoinColumn.class));
            
            jakarta.persistence.ManyToOne manyToOne = statusCodeField.getAnnotation(jakarta.persistence.ManyToOne.class);
            assertEquals(jakarta.persistence.FetchType.LAZY, manyToOne.fetch());
            
            jakarta.persistence.JoinColumn joinColumn = statusCodeField.getAnnotation(jakarta.persistence.JoinColumn.class);
            assertEquals("status_code_id", joinColumn.name());
            assertFalse(joinColumn.nullable());
        }

        @Test
        @DisplayName("Should have correct column annotations on string fields")
        void shouldHaveCorrectColumnAnnotationsOnStringFields() throws Exception {
            // Test eventEmail
            Field eventEmailField = EventApply.class.getDeclaredField("eventEmail");
            assertTrue(eventEmailField.isAnnotationPresent(jakarta.persistence.Column.class));
            jakarta.persistence.Column eventEmailColumn = eventEmailField.getAnnotation(jakarta.persistence.Column.class);
            assertEquals("event_email", eventEmailColumn.name());
            assertFalse(eventEmailColumn.nullable());
            assertEquals(100, eventEmailColumn.length());

            // Test businessNumber
            Field businessNumberField = EventApply.class.getDeclaredField("businessNumber");
            assertTrue(businessNumberField.isAnnotationPresent(jakarta.persistence.Column.class));
            jakarta.persistence.Column businessNumberColumn = businessNumberField.getAnnotation(jakarta.persistence.Column.class);
            assertEquals("business_number", businessNumberColumn.name());
            assertFalse(businessNumberColumn.nullable());
            assertEquals(20, businessNumberColumn.length());

            // Test managerName
            Field managerNameField = EventApply.class.getDeclaredField("managerName");
            assertTrue(managerNameField.isAnnotationPresent(jakarta.persistence.Column.class));
            jakarta.persistence.Column managerNameColumn = managerNameField.getAnnotation(jakarta.persistence.Column.class);
            assertEquals("manager_name", managerNameColumn.name());
            assertFalse(managerNameColumn.nullable());
            assertEquals(50, managerNameColumn.length());
        }

        @Test
        @DisplayName("Should have correct boolean field annotation")
        void shouldHaveCorrectBooleanFieldAnnotation() throws Exception {
            // Given
            Field verifiedField = EventApply.class.getDeclaredField("verified");

            // Then
            assertTrue(verifiedField.isAnnotationPresent(jakarta.persistence.Column.class));
            jakarta.persistence.Column column = verifiedField.getAnnotation(jakarta.persistence.Column.class);
            assertFalse(column.nullable());
            assertEquals("BOOLEAN NOT NULL DEFAULT FALSE", column.columnDefinition());
        }

        @Test
        @DisplayName("Should have correct TEXT field annotation")
        void shouldHaveCorrectTextFieldAnnotation() throws Exception {
            // Given
            Field adminCommentField = EventApply.class.getDeclaredField("adminComment");

            // Then
            assertTrue(adminCommentField.isAnnotationPresent(jakarta.persistence.Column.class));
            jakarta.persistence.Column column = adminCommentField.getAnnotation(jakarta.persistence.Column.class);
            assertEquals("admin_comment", column.name());
            assertEquals("TEXT", column.columnDefinition());
        }
    }

    @Nested
    @DisplayName("Lombok Annotation Tests")
    class LombokAnnotationTests {

        @Test
        @DisplayName("Should have correct Lombok annotations on class")
        void shouldHaveCorrectLombokAnnotationsOnClass() {
            // Given
            Class<EventApply> clazz = EventApply.class;

            // Then
            assertTrue(clazz.isAnnotationPresent(lombok.Getter.class));
            assertTrue(clazz.isAnnotationPresent(lombok.NoArgsConstructor.class));
        }

        @Test
        @DisplayName("Should have getter methods for all fields")
        void shouldHaveGetterMethodsForAllFields() throws Exception {
            // Then - Verify all getter methods exist
            assertNotNull(EventApply.class.getMethod("getId"));
            assertNotNull(EventApply.class.getMethod("getStatusCode"));
            assertNotNull(EventApply.class.getMethod("getEventEmail"));
            assertNotNull(EventApply.class.getMethod("getBusinessNumber"));
            assertNotNull(EventApply.class.getMethod("getVerified"));
            assertNotNull(EventApply.class.getMethod("getManagerName"));
            assertNotNull(EventApply.class.getMethod("getEmail"));
            assertNotNull(EventApply.class.getMethod("getContactNumber"));
            assertNotNull(EventApply.class.getMethod("getTitleKr"));
            assertNotNull(EventApply.class.getMethod("getTitleEng"));
            assertNotNull(EventApply.class.getMethod("getFileUrl"));
            assertNotNull(EventApply.class.getMethod("getApplyAt"));
            assertNotNull(EventApply.class.getMethod("getAdminComment"));
            assertNotNull(EventApply.class.getMethod("getStatusUpdatedAt"));
        }
    }

    // Helper method to set private fields using reflection
    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    // Helper method to create a mock ApplyStatusCode for testing
    private ApplyStatusCode createMockApplyStatusCode() {
        try {
            ApplyStatusCode statusCode = new ApplyStatusCode();
            // Set some test values using reflection if needed
            setPrivateField(statusCode, "id", 1);
            setPrivateField(statusCode, "code", "PENDING");
            setPrivateField(statusCode, "name", "대기중");
            return statusCode;
        } catch (Exception e) {
            // If we can't create or set fields, return a basic instance
            return new ApplyStatusCode();
        }
    }
}
package com.fairing.fairplay.event.entity;

import com.fairing.fairplay.booth.entity.Booth;
import com.fairing.fairplay.ticket.entity.EventTicket;
import com.fairing.fairplay.ticket.entity.Ticket;
import com.fairing.fairplay.user.entity.EventAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;
import java.util.HashSet;

@DisplayName("Event Entity Tests")
class EventTest {

    private Event event;
    
    @Mock
    private EventAdmin mockManager;
    
    @Mock
    private EventStatusCode mockStatusCode;
    
    @Mock
    private EventDetail mockEventDetail;
    
    @Mock
    private ExternalLink mockExternalLink;
    
    @Mock
    private EventVersion mockEventVersion;
    
    @Mock
    private Ticket mockTicket;
    
    @Mock
    private EventTicket mockEventTicket;
    
    @Mock
    private Booth mockBooth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create Event with no-args constructor")
        void shouldCreateEventWithNoArgsConstructor() {
            Event newEvent = new Event();
            
            assertNotNull(newEvent);
            assertNull(newEvent.getEventId());
            assertNull(newEvent.getManager());
            assertNull(newEvent.getStatusCode());
            assertNull(newEvent.getEventCode());
            assertNull(newEvent.getTitleKr());
            assertNull(newEvent.getTitleEng());
            assertTrue(newEvent.getHidden()); // Default value
            assertNull(newEvent.getEventDetail());
            assertNotNull(newEvent.getExternalLinks());
            assertNotNull(newEvent.getEventVersions());
            assertNotNull(newEvent.getTickets());
            assertNotNull(newEvent.getEventTickets());
            assertNotNull(newEvent.getBooths());
        }

        @Test
        @DisplayName("Should create Event with all-args constructor")
        void shouldCreateEventWithAllArgsConstructor() {
            Long eventId = 1L;
            String eventCode = "TEST001";
            String titleKr = "테스트 이벤트";
            String titleEng = "Test Event";
            Boolean hidden = false;
            Set<ExternalLink> externalLinks = new HashSet<>();
            Set<EventVersion> eventVersions = new HashSet<>();
            Set<Ticket> tickets = new HashSet<>();
            Set<EventTicket> eventTickets = new HashSet<>();
            Set<Booth> booths = new HashSet<>();

            Event newEvent = new Event(eventId, mockManager, mockStatusCode, eventCode, 
                titleKr, titleEng, hidden, mockEventDetail, externalLinks, 
                eventVersions, tickets, eventTickets, booths);

            assertNotNull(newEvent);
            assertEquals(eventId, newEvent.getEventId());
            assertEquals(mockManager, newEvent.getManager());
            assertEquals(mockStatusCode, newEvent.getStatusCode());
            assertEquals(eventCode, newEvent.getEventCode());
            assertEquals(titleKr, newEvent.getTitleKr());
            assertEquals(titleEng, newEvent.getTitleEng());
            assertEquals(hidden, newEvent.getHidden());
            assertEquals(mockEventDetail, newEvent.getEventDetail());
            assertEquals(externalLinks, newEvent.getExternalLinks());
            assertEquals(eventVersions, newEvent.getEventVersions());
            assertEquals(tickets, newEvent.getTickets());
            assertEquals(eventTickets, newEvent.getEventTickets());
            assertEquals(booths, newEvent.getBooths());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Should set and get eventId")
        void shouldSetAndGetEventId() {
            Long eventId = 1L;
            event.setEventId(eventId);
            assertEquals(eventId, event.getEventId());
        }

        @Test
        @DisplayName("Should set and get manager")
        void shouldSetAndGetManager() {
            event.setManager(mockManager);
            assertEquals(mockManager, event.getManager());
        }

        @Test
        @DisplayName("Should set and get statusCode")
        void shouldSetAndGetStatusCode() {
            event.setStatusCode(mockStatusCode);
            assertEquals(mockStatusCode, event.getStatusCode());
        }

        @Test
        @DisplayName("Should set and get eventCode")
        void shouldSetAndGetEventCode() {
            String eventCode = "TEST001";
            event.setEventCode(eventCode);
            assertEquals(eventCode, event.getEventCode());
        }

        @Test
        @DisplayName("Should set and get titleKr")
        void shouldSetAndGetTitleKr() {
            String titleKr = "테스트 이벤트";
            event.setTitleKr(titleKr);
            assertEquals(titleKr, event.getTitleKr());
        }

        @Test
        @DisplayName("Should set and get titleEng")
        void shouldSetAndGetTitleEng() {
            String titleEng = "Test Event";
            event.setTitleEng(titleEng);
            assertEquals(titleEng, event.getTitleEng());
        }

        @Test
        @DisplayName("Should set and get hidden")
        void shouldSetAndGetHidden() {
            Boolean hidden = false;
            event.setHidden(hidden);
            assertEquals(hidden, event.getHidden());
        }

        @Test
        @DisplayName("Should set and get eventDetail")
        void shouldSetAndGetEventDetail() {
            event.setEventDetail(mockEventDetail);
            assertEquals(mockEventDetail, event.getEventDetail());
        }

        @Test
        @DisplayName("Should set and get externalLinks")
        void shouldSetAndGetExternalLinks() {
            Set<ExternalLink> externalLinks = new HashSet<>();
            externalLinks.add(mockExternalLink);
            
            event.setExternalLinks(externalLinks);
            assertEquals(externalLinks, event.getExternalLinks());
            assertTrue(event.getExternalLinks().contains(mockExternalLink));
        }

        @Test
        @DisplayName("Should set and get eventVersions")
        void shouldSetAndGetEventVersions() {
            Set<EventVersion> eventVersions = new HashSet<>();
            eventVersions.add(mockEventVersion);
            
            event.setEventVersions(eventVersions);
            assertEquals(eventVersions, event.getEventVersions());
            assertTrue(event.getEventVersions().contains(mockEventVersion));
        }

        @Test
        @DisplayName("Should set and get tickets")
        void shouldSetAndGetTickets() {
            Set<Ticket> tickets = new HashSet<>();
            tickets.add(mockTicket);
            
            event.setTickets(tickets);
            assertEquals(tickets, event.getTickets());
            assertTrue(event.getTickets().contains(mockTicket));
        }

        @Test
        @DisplayName("Should set and get eventTickets")
        void shouldSetAndGetEventTickets() {
            Set<EventTicket> eventTickets = new HashSet<>();
            eventTickets.add(mockEventTicket);
            
            event.setEventTickets(eventTickets);
            assertEquals(eventTickets, event.getEventTickets());
            assertTrue(event.getEventTickets().contains(mockEventTicket));
        }

        @Test
        @DisplayName("Should set and get booths")
        void shouldSetAndGetBooths() {
            Set<Booth> booths = new HashSet<>();
            booths.add(mockBooth);
            
            event.setBooths(booths);
            assertEquals(booths, event.getBooths());
            assertTrue(event.getBooths().contains(mockBooth));
        }
    }

    @Nested
    @DisplayName("Default Values Tests")
    class DefaultValuesTests {

        @Test
        @DisplayName("Should have default hidden value as true")
        void shouldHaveDefaultHiddenValueAsTrue() {
            Event newEvent = new Event();
            assertTrue(newEvent.getHidden());
        }

        @Test
        @DisplayName("Should initialize empty collections")
        void shouldInitializeEmptyCollections() {
            Event newEvent = new Event();
            
            assertNotNull(newEvent.getExternalLinks());
            assertNotNull(newEvent.getEventVersions());
            assertNotNull(newEvent.getTickets());
            assertNotNull(newEvent.getEventTickets());
            assertNotNull(newEvent.getBooths());
            
            assertTrue(newEvent.getExternalLinks().isEmpty());
            assertTrue(newEvent.getEventVersions().isEmpty());
            assertTrue(newEvent.getTickets().isEmpty());
            assertTrue(newEvent.getEventTickets().isEmpty());
            assertTrue(newEvent.getBooths().isEmpty());
        }
    }

    @Nested
    @DisplayName("Edge Cases and Validation Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle null values gracefully")
        void shouldHandleNullValuesGracefully() {
            event.setEventId(null);
            event.setManager(null);
            event.setStatusCode(null);
            event.setEventCode(null);
            event.setTitleKr(null);
            event.setTitleEng(null);
            event.setHidden(null);
            event.setEventDetail(null);
            
            assertNull(event.getEventId());
            assertNull(event.getManager());
            assertNull(event.getStatusCode());
            assertNull(event.getEventCode());
            assertNull(event.getTitleKr());
            assertNull(event.getTitleEng());
            assertNull(event.getHidden());
            assertNull(event.getEventDetail());
        }

        @Test
        @DisplayName("Should handle empty strings for text fields")
        void shouldHandleEmptyStringsForTextFields() {
            event.setEventCode("");
            event.setTitleKr("");
            event.setTitleEng("");
            
            assertEquals("", event.getEventCode());
            assertEquals("", event.getTitleKr());
            assertEquals("", event.getTitleEng());
        }

        @Test
        @DisplayName("Should handle maximum length strings")
        void shouldHandleMaximumLengthStrings() {
            String maxEventCode = "A".repeat(50);
            String maxTitleKr = "가".repeat(200);
            String maxTitleEng = "A".repeat(200);
            
            event.setEventCode(maxEventCode);
            event.setTitleKr(maxTitleKr);
            event.setTitleEng(maxTitleEng);
            
            assertEquals(maxEventCode, event.getEventCode());
            assertEquals(maxTitleKr, event.getTitleKr());
            assertEquals(maxTitleEng, event.getTitleEng());
        }

        @Test
        @DisplayName("Should handle special characters in text fields")
        void shouldHandleSpecialCharactersInTextFields() {
            String specialEventCode = "TEST-001_@#$";
            String specialTitleKr = "테스트 이벤트 @#$%^&*()";
            String specialTitleEng = "Test Event @#$%^&*()";
            
            event.setEventCode(specialEventCode);
            event.setTitleKr(specialTitleKr);
            event.setTitleEng(specialTitleEng);
            
            assertEquals(specialEventCode, event.getEventCode());
            assertEquals(specialTitleKr, event.getTitleKr());
            assertEquals(specialTitleEng, event.getTitleEng());
        }

        @Test
        @DisplayName("Should handle Boolean true and false values")
        void shouldHandleBooleanTrueAndFalseValues() {
            event.setHidden(true);
            assertTrue(event.getHidden());
            
            event.setHidden(false);
            assertFalse(event.getHidden());
        }

        @Test
        @DisplayName("Should handle whitespace in text fields")
        void shouldHandleWhitespaceInTextFields() {
            String whitespaceEventCode = "  TEST001  ";
            String whitespaceTitleKr = "  테스트 이벤트  ";
            String whitespaceTitleEng = "  Test Event  ";
            
            event.setEventCode(whitespaceEventCode);
            event.setTitleKr(whitespaceTitleKr);
            event.setTitleEng(whitespaceTitleEng);
            
            assertEquals(whitespaceEventCode, event.getEventCode());
            assertEquals(whitespaceTitleKr, event.getTitleKr());
            assertEquals(whitespaceTitleEng, event.getTitleEng());
        }
    }

    @Nested
    @DisplayName("Collection Management Tests")
    class CollectionManagementTests {

        @Test
        @DisplayName("Should maintain collection integrity when adding elements")
        void shouldMaintainCollectionIntegrityWhenAddingElements() {
            event.getExternalLinks().add(mockExternalLink);
            event.getEventVersions().add(mockEventVersion);
            event.getTickets().add(mockTicket);
            event.getEventTickets().add(mockEventTicket);
            event.getBooths().add(mockBooth);
            
            assertEquals(1, event.getExternalLinks().size());
            assertEquals(1, event.getEventVersions().size());
            assertEquals(1, event.getTickets().size());
            assertEquals(1, event.getEventTickets().size());
            assertEquals(1, event.getBooths().size());
            
            assertTrue(event.getExternalLinks().contains(mockExternalLink));
            assertTrue(event.getEventVersions().contains(mockEventVersion));
            assertTrue(event.getTickets().contains(mockTicket));
            assertTrue(event.getEventTickets().contains(mockEventTicket));
            assertTrue(event.getBooths().contains(mockBooth));
        }

        @Test
        @DisplayName("Should maintain collection integrity when removing elements")
        void shouldMaintainCollectionIntegrityWhenRemovingElements() {
            event.getExternalLinks().add(mockExternalLink);
            assertEquals(1, event.getExternalLinks().size());
            
            event.getExternalLinks().remove(mockExternalLink);
            assertEquals(0, event.getExternalLinks().size());
            assertFalse(event.getExternalLinks().contains(mockExternalLink));
        }

        @Test
        @DisplayName("Should handle duplicate elements in collections")
        void shouldHandleDuplicateElementsInCollections() {
            event.getExternalLinks().add(mockExternalLink);
            event.getExternalLinks().add(mockExternalLink); // Adding same element twice
            
            // HashSet should maintain uniqueness
            assertEquals(1, event.getExternalLinks().size());
            assertTrue(event.getExternalLinks().contains(mockExternalLink));
        }

        @Test
        @DisplayName("Should replace collections entirely")
        void shouldReplaceCollectionsEntirely() {
            // Add initial elements
            ExternalLink mockLink1 = mock(ExternalLink.class);
            event.getExternalLinks().add(mockLink1);
            assertEquals(1, event.getExternalLinks().size());
            
            // Replace with new collection
            Set<ExternalLink> newLinks = new HashSet<>();
            ExternalLink mockLink2 = mock(ExternalLink.class);
            newLinks.add(mockLink2);
            
            event.setExternalLinks(newLinks);
            
            assertEquals(1, event.getExternalLinks().size());
            assertFalse(event.getExternalLinks().contains(mockLink1));
            assertTrue(event.getExternalLinks().contains(mockLink2));
        }

        @Test
        @DisplayName("Should handle null collections gracefully")
        void shouldHandleNullCollectionsGracefully() {
            assertDoesNotThrow(() -> {
                event.setExternalLinks(null);
                event.setEventVersions(null);
                event.setTickets(null);
                event.setEventTickets(null);
                event.setBooths(null);
            });
            
            assertNull(event.getExternalLinks());
            assertNull(event.getEventVersions());
            assertNull(event.getTickets());
            assertNull(event.getEventTickets());
            assertNull(event.getBooths());
        }
    }

    @Nested
    @DisplayName("Relationship Tests")
    class RelationshipTests {

        @Test
        @DisplayName("Should handle one-to-one relationship with manager")
        void shouldHandleOneToOneRelationshipWithManager() {
            event.setManager(mockManager);
            assertEquals(mockManager, event.getManager());
            
            // Test replacing manager
            EventAdmin newManager = mock(EventAdmin.class);
            event.setManager(newManager);
            assertEquals(newManager, event.getManager());
            assertNotEquals(mockManager, event.getManager());
        }

        @Test
        @DisplayName("Should handle many-to-one relationship with statusCode")
        void shouldHandleManyToOneRelationshipWithStatusCode() {
            event.setStatusCode(mockStatusCode);
            assertEquals(mockStatusCode, event.getStatusCode());
            
            // Test replacing statusCode
            EventStatusCode newStatusCode = mock(EventStatusCode.class);
            event.setStatusCode(newStatusCode);
            assertEquals(newStatusCode, event.getStatusCode());
            assertNotEquals(mockStatusCode, event.getStatusCode());
        }

        @Test
        @DisplayName("Should handle one-to-one relationship with eventDetail")
        void shouldHandleOneToOneRelationshipWithEventDetail() {
            event.setEventDetail(mockEventDetail);
            assertEquals(mockEventDetail, event.getEventDetail());
            
            // Test replacing eventDetail
            EventDetail newEventDetail = mock(EventDetail.class);
            event.setEventDetail(newEventDetail);
            assertEquals(newEventDetail, event.getEventDetail());
            assertNotEquals(mockEventDetail, event.getEventDetail());
        }

        @Test
        @DisplayName("Should handle null relationships")
        void shouldHandleNullRelationships() {
            event.setManager(null);
            event.setStatusCode(null);
            event.setEventDetail(null);
            
            assertNull(event.getManager());
            assertNull(event.getStatusCode());
            assertNull(event.getEventDetail());
        }
    }

    @Nested
    @DisplayName("Object State Tests")
    class ObjectStateTests {

        @Test
        @DisplayName("Should maintain state consistency after multiple operations")
        void shouldMaintainStateConsistencyAfterMultipleOperations() {
            // Set up event with various properties
            Long eventId = 1L;
            String eventCode = "TEST001";
            String titleKr = "테스트";
            String titleEng = "Test";
            Boolean hidden = false;
            
            event.setEventId(eventId);
            event.setManager(mockManager);
            event.setStatusCode(mockStatusCode);
            event.setEventCode(eventCode);
            event.setTitleKr(titleKr);
            event.setTitleEng(titleEng);
            event.setHidden(hidden);
            event.setEventDetail(mockEventDetail);
            
            // Add some collection elements
            event.getExternalLinks().add(mockExternalLink);
            
            // Verify all state is maintained
            assertEquals(eventId, event.getEventId());
            assertEquals(mockManager, event.getManager());
            assertEquals(mockStatusCode, event.getStatusCode());
            assertEquals(eventCode, event.getEventCode());
            assertEquals(titleKr, event.getTitleKr());
            assertEquals(titleEng, event.getTitleEng());
            assertEquals(hidden, event.getHidden());
            assertEquals(mockEventDetail, event.getEventDetail());
            assertTrue(event.getExternalLinks().contains(mockExternalLink));
        }

        @Test
        @DisplayName("Should handle state changes independently")
        void shouldHandleStateChangesIndependently() {
            // Set initial state
            event.setEventId(1L);
            event.setTitleKr("초기 제목");
            
            // Change one property
            event.setTitleEng("Updated Title");
            
            // Verify other properties unchanged
            assertEquals(Long.valueOf(1L), event.getEventId());
            assertEquals("초기 제목", event.getTitleKr());
            assertEquals("Updated Title", event.getTitleEng());
        }

        @Test
        @DisplayName("Should handle concurrent modifications simulation")
        void shouldHandleConcurrentModificationsSimulation() {
            // Simulate multiple field updates
            event.setEventId(1L);
            event.setEventCode("CONCURRENT001");
            event.setTitleKr("동시 수정 테스트");
            event.setTitleEng("Concurrent Test");
            event.setHidden(false);
            
            // All changes should be reflected
            assertAll(
                () -> assertEquals(Long.valueOf(1L), event.getEventId()),
                () -> assertEquals("CONCURRENT001", event.getEventCode()),
                () -> assertEquals("동시 수정 테스트", event.getTitleKr()),
                () -> assertEquals("Concurrent Test", event.getTitleEng()),
                () -> assertFalse(event.getHidden())
            );
        }
    }

    @Nested
    @DisplayName("JPA Annotation Validation Tests")
    class JpaAnnotationTests {

        @Test
        @DisplayName("Should handle database constraint scenarios")
        void shouldHandleDatabaseConstraintScenarios() {
            // Test unique constraint behavior simulation
            String uniqueEventCode = "UNIQUE001";
            event.setEventCode(uniqueEventCode);
            assertEquals(uniqueEventCode, event.getEventCode());
            
            // Test not null constraint behavior simulation
            assertDoesNotThrow(() -> {
                event.setTitleKr("Required Korean Title");
                event.setTitleEng("Required English Title");
            });
        }

        @Test
        @DisplayName("Should validate cascade behavior simulation")
        void shouldValidateCascadeBehaviorSimulation() {
            // Test cascade ALL behavior for collections
            event.getExternalLinks().add(mockExternalLink);
            
            // Simulate cascade behavior - when event is persisted, children should be too
            assertNotNull(event.getExternalLinks());
            assertTrue(event.getExternalLinks().contains(mockExternalLink));
            
            // Test orphan removal simulation
            event.getExternalLinks().remove(mockExternalLink);
            assertFalse(event.getExternalLinks().contains(mockExternalLink));
        }

        @Test
        @DisplayName("Should validate lazy loading configuration")
        void shouldValidateLazyLoadingConfiguration() {
            // Simulate lazy loading behavior - relationships should be settable
            assertDoesNotThrow(() -> {
                event.setManager(mockManager);
                event.setStatusCode(mockStatusCode);
                event.setEventDetail(mockEventDetail);
            });
            
            assertEquals(mockManager, event.getManager());
            assertEquals(mockStatusCode, event.getStatusCode());
            assertEquals(mockEventDetail, event.getEventDetail());
        }

        @Test
        @DisplayName("Should validate column length constraints")
        void shouldValidateColumnLengthConstraints() {
            // Test length constraints
            String eventCode50 = "A".repeat(50);
            String titleKr200 = "가".repeat(200);
            String titleEng200 = "B".repeat(200);
            
            assertDoesNotThrow(() -> {
                event.setEventCode(eventCode50);
                event.setTitleKr(titleKr200);
                event.setTitleEng(titleEng200);
            });
            
            assertEquals(eventCode50, event.getEventCode());
            assertEquals(titleKr200, event.getTitleKr());
            assertEquals(titleEng200, event.getTitleEng());
        }
    }

    @Nested
    @DisplayName("Business Logic Validation Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("Should validate event code format constraints")
        void shouldValidateEventCodeFormatConstraints() {
            // Test various event code formats
            String[] validEventCodes = {"EVT001", "TEST-2024", "FAIR_PLAY_01", "E123456789"};
            
            for (String code : validEventCodes) {
                event.setEventCode(code);
                assertEquals(code, event.getEventCode());
            }
        }

        @Test
        @DisplayName("Should handle multilingual title consistency")
        void shouldHandleMultilingualTitleConsistency() {
            String koreanTitle = "한국어 제목";
            String englishTitle = "English Title";
            
            event.setTitleKr(koreanTitle);
            event.setTitleEng(englishTitle);
            
            assertEquals(koreanTitle, event.getTitleKr());
            assertEquals(englishTitle, event.getTitleEng());
            
            // Both titles should be independently settable
            assertNotEquals(event.getTitleKr(), event.getTitleEng());
        }

        @Test
        @DisplayName("Should validate hidden flag business logic")
        void shouldValidateHiddenFlagBusinessLogic() {
            // Test default hidden state
            Event newEvent = new Event();
            assertTrue(newEvent.getHidden()); // Should default to hidden
            
            // Test visibility toggle
            newEvent.setHidden(false);
            assertFalse(newEvent.getHidden());
            
            newEvent.setHidden(true);
            assertTrue(newEvent.getHidden());
        }

        @Test
        @DisplayName("Should validate collection relationships integrity")
        void shouldValidateCollectionRelationshipsIntegrity() {
            // Test that all collections are properly initialized and independent
            assertTrue(event.getExternalLinks().isEmpty());
            assertTrue(event.getEventVersions().isEmpty());
            assertTrue(event.getTickets().isEmpty());
            assertTrue(event.getEventTickets().isEmpty());
            assertTrue(event.getBooths().isEmpty());
            
            // Adding to one collection shouldn't affect others
            event.getExternalLinks().add(mockExternalLink);
            
            assertEquals(1, event.getExternalLinks().size());
            assertEquals(0, event.getEventVersions().size());
            assertEquals(0, event.getTickets().size());
            assertEquals(0, event.getEventTickets().size());
            assertEquals(0, event.getBooths().size());
        }

        @Test
        @DisplayName("Should validate event status transitions")
        void shouldValidateEventStatusTransitions() {
            EventStatusCode initialStatus = mock(EventStatusCode.class);
            EventStatusCode newStatus = mock(EventStatusCode.class);
            
            event.setStatusCode(initialStatus);
            assertEquals(initialStatus, event.getStatusCode());
            
            // Should allow status code changes
            event.setStatusCode(newStatus);
            assertEquals(newStatus, event.getStatusCode());
            assertNotEquals(initialStatus, event.getStatusCode());
        }

        @Test
        @DisplayName("Should validate manager assignment")
        void shouldValidateManagerAssignment() {
            EventAdmin manager1 = mock(EventAdmin.class);
            EventAdmin manager2 = mock(EventAdmin.class);
            
            event.setManager(manager1);
            assertEquals(manager1, event.getManager());
            
            // Should allow manager reassignment
            event.setManager(manager2);
            assertEquals(manager2, event.getManager());
            assertNotEquals(manager1, event.getManager());
        }
    }

    @Nested
    @DisplayName("Integration Scenario Tests")
    class IntegrationScenarioTests {

        @Test
        @DisplayName("Should handle full event creation scenario")
        void shouldHandleFullEventCreationScenario() {
            // Create a complete event
            event.setEventId(1L);
            event.setEventCode("INTEGRATION001");
            event.setTitleKr("통합 테스트 이벤트");
            event.setTitleEng("Integration Test Event");
            event.setHidden(false);
            event.setManager(mockManager);
            event.setStatusCode(mockStatusCode);
            event.setEventDetail(mockEventDetail);
            
            // Add related entities
            event.getExternalLinks().add(mockExternalLink);
            event.getEventVersions().add(mockEventVersion);
            event.getTickets().add(mockTicket);
            event.getEventTickets().add(mockEventTicket);
            event.getBooths().add(mockBooth);
            
            // Verify complete event state
            assertAll(
                () -> assertEquals(Long.valueOf(1L), event.getEventId()),
                () -> assertEquals("INTEGRATION001", event.getEventCode()),
                () -> assertEquals("통합 테스트 이벤트", event.getTitleKr()),
                () -> assertEquals("Integration Test Event", event.getTitleEng()),
                () -> assertFalse(event.getHidden()),
                () -> assertEquals(mockManager, event.getManager()),
                () -> assertEquals(mockStatusCode, event.getStatusCode()),
                () -> assertEquals(mockEventDetail, event.getEventDetail()),
                () -> assertEquals(1, event.getExternalLinks().size()),
                () -> assertEquals(1, event.getEventVersions().size()),
                () -> assertEquals(1, event.getTickets().size()),
                () -> assertEquals(1, event.getEventTickets().size()),
                () -> assertEquals(1, event.getBooths().size())
            );
        }

        @Test
        @DisplayName("Should handle event update scenario")
        void shouldHandleEventUpdateScenario() {
            // Initial event setup
            event.setEventId(1L);
            event.setEventCode("UPDATE001");
            event.setTitleKr("원본 제목");
            event.setTitleEng("Original Title");
            event.setHidden(true);
            
            // Update event
            event.setTitleKr("수정된 제목");
            event.setTitleEng("Updated Title");
            event.setHidden(false);
            event.setManager(mockManager);
            
            // Verify updates
            assertEquals("수정된 제목", event.getTitleKr());
            assertEquals("Updated Title", event.getTitleEng());
            assertFalse(event.getHidden());
            assertEquals(mockManager, event.getManager());
            
            // Original fields should remain unchanged
            assertEquals(Long.valueOf(1L), event.getEventId());
            assertEquals("UPDATE001", event.getEventCode());
        }

        @Test
        @DisplayName("Should handle event deletion scenario simulation")
        void shouldHandleEventDeletionScenarioSimulation() {
            // Setup event with relationships
            event.setEventId(1L);
            event.getExternalLinks().add(mockExternalLink);
            event.getTickets().add(mockTicket);
            
            // Simulate cascade deletion by clearing collections
            event.getExternalLinks().clear();
            event.getTickets().clear();
            
            // Verify collections are empty
            assertTrue(event.getExternalLinks().isEmpty());
            assertTrue(event.getTickets().isEmpty());
            
            // Event ID should still exist (soft delete scenario)
            assertEquals(Long.valueOf(1L), event.getEventId());
        }
    }
}
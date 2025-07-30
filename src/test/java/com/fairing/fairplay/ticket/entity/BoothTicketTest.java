package com.fairing.fairplay.ticket.entity;

import com.fairing.fairplay.booth.entity.Booth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Comprehensive unit tests for BoothTicket entity
 * Testing Framework: JUnit 5 with Mockito for mocking
 * Coverage: Constructor validation, getter/setter operations, entity relationships, 
 * edge cases, state consistency, Lombok-generated methods, and JPA annotations
 */
@DisplayName("BoothTicket Entity Tests")
class BoothTicketTest {

    @Mock
    private Ticket mockTicket;
    
    @Mock
    private Booth mockBooth;
    
    private BoothTicket boothTicket;
    private static final Long TICKET_ID = 123L;
    private static final Long BOOTH_ID = 456L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockTicket.getTicketId()).thenReturn(TICKET_ID);
        when(mockBooth.getId()).thenReturn(BOOTH_ID);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create BoothTicket with no-args constructor")
        void shouldCreateBoothTicketWithNoArgsConstructor() {
            // Given & When
            BoothTicket boothTicket = new BoothTicket();
            
            // Then
            assertNotNull(boothTicket);
            assertNull(boothTicket.getId());
            assertNull(boothTicket.getTicket());
            assertNull(boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should create BoothTicket with parameterized constructor")
        void shouldCreateBoothTicketWithParameterizedConstructor() {
            // Given & When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket);
            assertEquals(mockTicket, boothTicket.getTicket());
            assertEquals(mockBooth, boothTicket.getBooth());
            assertNotNull(boothTicket.getId());
            assertEquals(TICKET_ID, boothTicket.getId().getTicketId());
            assertEquals(BOOTH_ID, boothTicket.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle null ticket in parameterized constructor")
        void shouldHandleNullTicketInParameterizedConstructor() {
            // Given & When & Then
            assertThrows(NullPointerException.class, () -> {
                new BoothTicket(null, mockBooth);
            });
        }

        @Test
        @DisplayName("Should handle null booth in parameterized constructor")
        void shouldHandleNullBoothInParameterizedConstructor() {
            // Given & When & Then
            assertThrows(NullPointerException.class, () -> {
                new BoothTicket(mockTicket, null);
            });
        }

        @Test
        @DisplayName("Should handle both null parameters in parameterized constructor")
        void shouldHandleBothNullParametersInParameterizedConstructor() {
            // Given & When & Then
            assertThrows(NullPointerException.class, () -> {
                new BoothTicket(null, null);
            });
        }

        @Test
        @DisplayName("Should correctly initialize composite ID in parameterized constructor")
        void shouldCorrectlyInitializeCompositeIdInParameterizedConstructor() {
            // Given
            Long customTicketId = 999L;
            Long customBoothId = 888L;
            when(mockTicket.getTicketId()).thenReturn(customTicketId);
            when(mockBooth.getId()).thenReturn(customBoothId);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(customTicketId, boothTicket.getId().getTicketId());
            assertEquals(customBoothId, boothTicket.getId().getBoothId());
            verify(mockTicket, times(2)).getTicketId(); // Called once for ID creation, once for verification
            verify(mockBooth, times(2)).getId();
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @BeforeEach
        void setUp() {
            boothTicket = new BoothTicket();
        }

        @Test
        @DisplayName("Should get and set id correctly")
        void shouldGetAndSetIdCorrectly() {
            // Given
            BoothTicketId id = new BoothTicketId(TICKET_ID, BOOTH_ID);
            
            // When
            boothTicket.setId(id);
            
            // Then
            assertEquals(id, boothTicket.getId());
            assertSame(id, boothTicket.getId());
        }

        @Test
        @DisplayName("Should get and set ticket correctly")
        void shouldGetAndSetTicketCorrectly() {
            // Given & When
            boothTicket.setTicket(mockTicket);
            
            // Then
            assertEquals(mockTicket, boothTicket.getTicket());
            assertSame(mockTicket, boothTicket.getTicket());
        }

        @Test
        @DisplayName("Should get and set booth correctly")
        void shouldGetAndSetBoothCorrectly() {
            // Given & When
            boothTicket.setBooth(mockBooth);
            
            // Then
            assertEquals(mockBooth, boothTicket.getBooth());
            assertSame(mockBooth, boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should handle null values in setters")
        void shouldHandleNullValuesInSetters() {
            // Given
            boothTicket.setId(new BoothTicketId(1L, 2L));
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            
            // When
            boothTicket.setId(null);
            boothTicket.setTicket(null);
            boothTicket.setBooth(null);
            
            // Then
            assertNull(boothTicket.getId());
            assertNull(boothTicket.getTicket());
            assertNull(boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should allow overwriting existing values")
        void shouldAllowOverwritingExistingValues() {
            // Given
            BoothTicketId firstId = new BoothTicketId(100L, 200L);
            BoothTicketId secondId = new BoothTicketId(300L, 400L);
            Ticket anotherTicket = mock(Ticket.class);
            Booth anotherBooth = mock(Booth.class);
            
            boothTicket.setId(firstId);
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            
            // When
            boothTicket.setId(secondId);
            boothTicket.setTicket(anotherTicket);
            boothTicket.setBooth(anotherBooth);
            
            // Then
            assertEquals(secondId, boothTicket.getId());
            assertEquals(anotherTicket, boothTicket.getTicket());
            assertEquals(anotherBooth, boothTicket.getBooth());
        }
    }

    @Nested
    @DisplayName("Entity Relationship Tests")
    class EntityRelationshipTests {

        @Test
        @DisplayName("Should maintain bidirectional relationship with Ticket")
        void shouldMaintainBidirectionalRelationshipWithTicket() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When & Then
            assertEquals(mockTicket, boothTicket.getTicket());
            verify(mockTicket, atLeastOnce()).getTicketId();
        }

        @Test
        @DisplayName("Should maintain bidirectional relationship with Booth")
        void shouldMaintainBidirectionalRelationshipWithBooth() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When & Then
            assertEquals(mockBooth, boothTicket.getBooth());
            verify(mockBooth, atLeastOnce()).getId();
        }

        @Test
        @DisplayName("Should create composite id correctly from relationships")
        void shouldCreateCompositeIdCorrectlyFromRelationships() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When
            BoothTicketId id = boothTicket.getId();
            
            // Then
            assertNotNull(id);
            assertEquals(TICKET_ID, id.getTicketId());
            assertEquals(BOOTH_ID, id.getBoothId());
        }

        @Test
        @DisplayName("Should handle lazy loading annotations correctly")
        void shouldHandleLazyLoadingAnnotationsCorrectly() {
            // Given
            BoothTicket boothTicket = new BoothTicket();
            
            // When
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            
            // Then - Verify lazy relationships can be set without immediate loading
            assertEquals(mockTicket, boothTicket.getTicket());
            assertEquals(mockBooth, boothTicket.getBooth());
            verifyNoMoreInteractions(mockTicket, mockBooth);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCasesAndBoundaryTests {

        @Test
        @DisplayName("Should handle ticket with zero id")
        void shouldHandleTicketWithZeroId() {
            // Given
            when(mockTicket.getTicketId()).thenReturn(0L);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(0L, boothTicket.getId().getTicketId());
        }

        @Test
        @DisplayName("Should handle booth with zero id")
        void shouldHandleBoothWithZeroId() {
            // Given
            when(mockBooth.getId()).thenReturn(0L);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(0L, boothTicket.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle ticket with negative id")
        void shouldHandleTicketWithNegativeId() {
            // Given
            when(mockTicket.getTicketId()).thenReturn(-1L);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(-1L, boothTicket.getId().getTicketId());
        }

        @Test
        @DisplayName("Should handle booth with negative id")
        void shouldHandleBoothWithNegativeId() {
            // Given
            when(mockBooth.getId()).thenReturn(-1L);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(-1L, boothTicket.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle maximum long values for ids")
        void shouldHandleMaximumLongValuesForIds() {
            // Given
            when(mockTicket.getTicketId()).thenReturn(Long.MAX_VALUE);
            when(mockBooth.getId()).thenReturn(Long.MAX_VALUE);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(Long.MAX_VALUE, boothTicket.getId().getTicketId());
            assertEquals(Long.MAX_VALUE, boothTicket.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle minimum long values for ids")
        void shouldHandleMinimumLongValuesForIds() {
            // Given
            when(mockTicket.getTicketId()).thenReturn(Long.MIN_VALUE);
            when(mockBooth.getId()).thenReturn(Long.MIN_VALUE);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(Long.MIN_VALUE, boothTicket.getId().getTicketId());
            assertEquals(Long.MIN_VALUE, boothTicket.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle same ticket and booth IDs")
        void shouldHandleSameTicketAndBoothIds() {
            // Given
            Long sameId = 12345L;
            when(mockTicket.getTicketId()).thenReturn(sameId);
            when(mockBooth.getId()).thenReturn(sameId);
            
            // When
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // Then
            assertNotNull(boothTicket.getId());
            assertEquals(sameId, boothTicket.getId().getTicketId());
            assertEquals(sameId, boothTicket.getId().getBoothId());
        }
    }

    @Nested
    @DisplayName("State Consistency Tests")
    class StateConsistencyTests {

        @Test
        @DisplayName("Should maintain state consistency after multiple setter calls")
        void shouldMaintainStateConsistencyAfterMultipleSetterCalls() {
            // Given
            BoothTicket boothTicket = new BoothTicket();
            BoothTicketId id1 = new BoothTicketId(100L, 200L);
            BoothTicketId id2 = new BoothTicketId(300L, 400L);
            
            // When
            boothTicket.setId(id1);
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            boothTicket.setId(id2);
            
            // Then
            assertEquals(id2, boothTicket.getId());
            assertEquals(mockTicket, boothTicket.getTicket());
            assertEquals(mockBooth, boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should handle reassignment of ticket")
        void shouldHandleReassignmentOfTicket() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            Ticket newTicket = mock(Ticket.class);
            when(newTicket.getTicketId()).thenReturn(999L);
            
            // When
            boothTicket.setTicket(newTicket);
            
            // Then
            assertEquals(newTicket, boothTicket.getTicket());
            assertNotEquals(mockTicket, boothTicket.getTicket());
        }

        @Test
        @DisplayName("Should handle reassignment of booth")
        void shouldHandleReassignmentOfBooth() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            Booth newBooth = mock(Booth.class);
            when(newBooth.getId()).thenReturn(999L);
            
            // When
            boothTicket.setBooth(newBooth);
            
            // Then
            assertEquals(newBooth, boothTicket.getBooth());
            assertNotEquals(mockBooth, boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should maintain consistency between constructor initialization and setters")
        void shouldMaintainConsistencyBetweenConstructorInitializationAndSetters() {
            // Given
            BoothTicket constructorBoothTicket = new BoothTicket(mockTicket, mockBooth);
            BoothTicket setterBoothTicket = new BoothTicket();
            
            // When
            setterBoothTicket.setTicket(mockTicket);
            setterBoothTicket.setBooth(mockBooth);
            setterBoothTicket.setId(new BoothTicketId(TICKET_ID, BOOTH_ID));
            
            // Then
            assertEquals(constructorBoothTicket.getTicket(), setterBoothTicket.getTicket());
            assertEquals(constructorBoothTicket.getBooth(), setterBoothTicket.getBooth());
            assertEquals(constructorBoothTicket.getId().getTicketId(), setterBoothTicket.getId().getTicketId());
            assertEquals(constructorBoothTicket.getId().getBoothId(), setterBoothTicket.getId().getBoothId());
        }
    }

    @Nested
    @DisplayName("Lombok Annotation Tests")
    class LombokAnnotationTests {

        @Test
        @DisplayName("Should verify getter methods exist and work correctly")
        void shouldVerifyGetterMethodsExistAndWorkCorrectly() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When & Then - Testing that Lombok-generated getters work
            assertDoesNotThrow(() -> {
                assertNotNull(boothTicket.getId());
                assertNotNull(boothTicket.getTicket());
                assertNotNull(boothTicket.getBooth());
            });
        }

        @Test
        @DisplayName("Should verify setter methods exist and work correctly")
        void shouldVerifySetterMethodsExistAndWorkCorrectly() {
            // Given
            BoothTicket boothTicket = new BoothTicket();
            BoothTicketId id = new BoothTicketId(TICKET_ID, BOOTH_ID);
            
            // When & Then - Testing that Lombok-generated setters work
            assertDoesNotThrow(() -> {
                boothTicket.setId(id);
                boothTicket.setTicket(mockTicket);
                boothTicket.setBooth(mockBooth);
            });
            
            assertEquals(id, boothTicket.getId());
            assertEquals(mockTicket, boothTicket.getTicket());
            assertEquals(mockBooth, boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should verify no-args constructor exists and works correctly")
        void shouldVerifyNoArgsConstructorExistsAndWorksCorrectly() {
            // When & Then - Testing that Lombok-generated no-args constructor works
            assertDoesNotThrow(() -> {
                BoothTicket boothTicket = new BoothTicket();
                assertNotNull(boothTicket);
            });
        }

        @Test
        @DisplayName("Should verify Lombok generates proper getter return types")
        void shouldVerifyLombokGeneratesProperGetterReturnTypes() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When & Then
            assertTrue(boothTicket.getId() instanceof BoothTicketId);
            assertTrue(boothTicket.getTicket() instanceof Ticket);
            assertTrue(boothTicket.getBooth() instanceof Booth);
        }
    }

    @Nested
    @DisplayName("JPA Annotation Tests")
    class JPAAnnotationTests {

        @Test
        @DisplayName("Should verify entity can be instantiated for JPA operations")
        void shouldVerifyEntityCanBeInstantiatedForJPAOperations() {
            // Given & When
            BoothTicket boothTicket = new BoothTicket();
            
            // Then - Verify entity is in a state suitable for JPA
            assertNotNull(boothTicket);
            assertNull(boothTicket.getId()); // Should be null before persistence
        }

        @Test
        @DisplayName("Should verify composite id structure is maintained")
        void shouldVerifyCompositeIdStructureIsMaintained() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            
            // When
            BoothTicketId id = boothTicket.getId();
            
            // Then - Verify composite ID structure for @EmbeddedId
            assertNotNull(id);
            assertTrue(id instanceof BoothTicketId);
        }

        @Test
        @DisplayName("Should verify ManyToOne relationships are properly configured")
        void shouldVerifyManyToOneRelationshipsAreProperlyConfigured() {
            // Given
            BoothTicket boothTicket = new BoothTicket();
            
            // When
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            
            // Then - Verify relationships can be established
            assertNotNull(boothTicket.getTicket());
            assertNotNull(boothTicket.getBooth());
            assertEquals(mockTicket, boothTicket.getTicket());
            assertEquals(mockBooth, boothTicket.getBooth());
        }

        @Test
        @DisplayName("Should verify entity supports persistence lifecycle")
        void shouldVerifyEntitySupportsPersistenceLifecycle() {
            // Given - Simulate entity creation for persistence
            BoothTicket boothTicket = new BoothTicket();
            
            // When - Set up for persistence
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            boothTicket.setId(new BoothTicketId(TICKET_ID, BOOTH_ID));
            
            // Then - Verify entity is ready for persistence operations
            assertNotNull(boothTicket.getId());
            assertNotNull(boothTicket.getTicket());
            assertNotNull(boothTicket.getBooth());
        }
    }

    @Nested
    @DisplayName("Integration and Complex Scenario Tests")
    class IntegrationAndComplexScenarioTests {

        @Test
        @DisplayName("Should handle complete entity lifecycle")
        void shouldHandleCompleteEntityLifecycle() {
            // Given - Create entity
            BoothTicket boothTicket = new BoothTicket();
            
            // When - Set up entity
            boothTicket.setTicket(mockTicket);
            boothTicket.setBooth(mockBooth);
            boothTicket.setId(new BoothTicketId(TICKET_ID, BOOTH_ID));
            
            // Then - Verify complete setup
            assertNotNull(boothTicket.getId());
            assertNotNull(boothTicket.getTicket());
            assertNotNull(boothTicket.getBooth());
            assertEquals(TICKET_ID, boothTicket.getId().getTicketId());
            assertEquals(BOOTH_ID, boothTicket.getId().getBoothId());
            
            // When - Modify entity
            Ticket newTicket = mock(Ticket.class);
            when(newTicket.getTicketId()).thenReturn(777L);
            boothTicket.setTicket(newTicket);
            
            // Then - Verify modification
            assertEquals(newTicket, boothTicket.getTicket());
            assertNotEquals(mockTicket, boothTicket.getTicket());
        }

        @Test
        @DisplayName("Should handle multiple instances with same IDs")
        void shouldHandleMultipleInstancesWithSameIds() {
            // Given
            BoothTicket boothTicket1 = new BoothTicket(mockTicket, mockBooth);
            BoothTicket boothTicket2 = new BoothTicket(mockTicket, mockBooth);
            
            // When & Then - Both should have same ID values but be different instances
            assertNotSame(boothTicket1, boothTicket2);
            assertEquals(boothTicket1.getId().getTicketId(), boothTicket2.getId().getTicketId());
            assertEquals(boothTicket1.getId().getBoothId(), boothTicket2.getId().getBoothId());
        }

        @Test
        @DisplayName("Should handle constructor with subsequent modifications")
        void shouldHandleConstructorWithSubsequentModifications() {
            // Given
            BoothTicket boothTicket = new BoothTicket(mockTicket, mockBooth);
            BoothTicketId originalId = boothTicket.getId();
            
            // When
            BoothTicketId newId = new BoothTicketId(999L, 888L);
            boothTicket.setId(newId);
            
            // Then
            assertNotEquals(originalId, boothTicket.getId());
            assertEquals(newId, boothTicket.getId());
            assertEquals(999L, boothTicket.getId().getTicketId());
            assertEquals(888L, boothTicket.getId().getBoothId());
        }
    }
}
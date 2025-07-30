package com.fairing.fairplay.ticket.entity;

import com.fairing.fairplay.event.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EventTicket Entity Tests")
class EventTicketTest {

    @Mock
    private Ticket mockTicket;

    @Mock
    private Event mockEvent;

    @Mock
    private EventTicketId mockEventTicketId;

    private EventTicket eventTicket;

    @BeforeEach
    void setUp() {
        eventTicket = new EventTicket();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create EventTicket with no-args constructor")
        void shouldCreateEventTicketWithNoArgsConstructor() {
            // Given & When
            EventTicket result = new EventTicket();

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getId()).isNull();
            assertThat(result.getTicket()).isNull();
            assertThat(result.getEvent()).isNull();
        }

        @Test
        @DisplayName("Should create EventTicket with ticket and event")
        void shouldCreateEventTicketWithTicketAndEvent() {
            // Given
            Long ticketId = 1L;
            Long eventId = 2L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getTicket()).isEqualTo(mockTicket);
            assertThat(result.getEvent()).isEqualTo(mockEvent);
            assertThat(result.getId()).isNotNull();
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle null ticket in constructor")
        void shouldHandleNullTicketInConstructor() {
            // Given
            Long eventId = 2L;
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When & Then
            assertThatThrownBy(() -> new EventTicket(null, mockEvent))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should handle null event in constructor")
        void shouldHandleNullEventInConstructor() {
            // Given
            Long ticketId = 1L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);

            // When & Then
            assertThatThrownBy(() -> new EventTicket(mockTicket, null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should handle both null parameters in constructor")
        void shouldHandleBothNullParametersInConstructor() {
            // When & Then
            assertThatThrownBy(() -> new EventTicket(null, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Should get and set id")
        void shouldGetAndSetId() {
            // When
            eventTicket.setId(mockEventTicketId);

            // Then
            assertThat(eventTicket.getId()).isEqualTo(mockEventTicketId);
        }

        @Test
        @DisplayName("Should get and set ticket")
        void shouldGetAndSetTicket() {
            // When
            eventTicket.setTicket(mockTicket);

            // Then
            assertThat(eventTicket.getTicket()).isEqualTo(mockTicket);
        }

        @Test
        @DisplayName("Should get and set event")
        void shouldGetAndSetEvent() {
            // When
            eventTicket.setEvent(mockEvent);

            // Then
            assertThat(eventTicket.getEvent()).isEqualTo(mockEvent);
        }

        @Test
        @DisplayName("Should handle null id setter")
        void shouldHandleNullIdSetter() {
            // When
            eventTicket.setId(null);

            // Then
            assertThat(eventTicket.getId()).isNull();
        }

        @Test
        @DisplayName("Should handle null ticket setter")
        void shouldHandleNullTicketSetter() {
            // When
            eventTicket.setTicket(null);

            // Then
            assertThat(eventTicket.getTicket()).isNull();
        }

        @Test
        @DisplayName("Should handle null event setter")
        void shouldHandleNullEventSetter() {
            // When
            eventTicket.setEvent(null);

            // Then
            assertThat(eventTicket.getEvent()).isNull();
        }
    }

    @Nested
    @DisplayName("Entity Relationship Tests")
    class EntityRelationshipTests {

        @Test
        @DisplayName("Should maintain bidirectional relationship with ticket")
        void shouldMaintainBidirectionalRelationshipWithTicket() {
            // Given
            Long ticketId = 1L;
            Long eventId = 2L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getTicket()).isEqualTo(mockTicket);
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            verify(mockTicket).getTicketId();
        }

        @Test
        @DisplayName("Should maintain bidirectional relationship with event")
        void shouldMaintainBidirectionalRelationshipWithEvent() {
            // Given
            Long ticketId = 1L;
            Long eventId = 2L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getEvent()).isEqualTo(mockEvent);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
            verify(mockEvent).getEventId();
        }

        @Test
        @DisplayName("Should create composite id from ticket and event ids")
        void shouldCreateCompositeIdFromTicketAndEventIds() {
            // Given
            Long ticketId = 100L;
            Long eventId = 200L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId()).isNotNull();
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }
    }

    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle zero ticket id")
        void shouldHandleZeroTicketId() {
            // Given
            Long ticketId = 0L;
            Long eventId = 1L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle zero event id")
        void shouldHandleZeroEventId() {
            // Given
            Long ticketId = 1L;
            Long eventId = 0L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle negative ticket id")
        void shouldHandleNegativeTicketId() {
            // Given
            Long ticketId = -1L;
            Long eventId = 1L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle negative event id")
        void shouldHandleNegativeEventId() {
            // Given
            Long ticketId = 1L;
            Long eventId = -1L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle maximum long values for ids")
        void shouldHandleMaximumLongValuesForIds() {
            // Given
            Long ticketId = Long.MAX_VALUE;
            Long eventId = Long.MAX_VALUE;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should handle minimum long values for ids")
        void shouldHandleMinimumLongValuesForIds() {
            // Given
            Long ticketId = Long.MIN_VALUE;
            Long eventId = Long.MIN_VALUE;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }
    }

    @Nested
    @DisplayName("State Consistency Tests")
    class StateConsistencyTests {

        @Test
        @DisplayName("Should maintain consistent state after multiple setter operations")
        void shouldMaintainConsistentStateAfterMultipleSetterOperations() {
            // Given
            EventTicketId newId = mock(EventTicketId.class);
            Ticket newTicket = mock(Ticket.class);
            Event newEvent = mock(Event.class);

            // When
            eventTicket.setId(mockEventTicketId);
            eventTicket.setTicket(mockTicket);
            eventTicket.setEvent(mockEvent);

            // Update with new values
            eventTicket.setId(newId);
            eventTicket.setTicket(newTicket);
            eventTicket.setEvent(newEvent);

            // Then
            assertThat(eventTicket.getId()).isEqualTo(newId);
            assertThat(eventTicket.getTicket()).isEqualTo(newTicket);
            assertThat(eventTicket.getEvent()).isEqualTo(newEvent);
        }

        @Test
        @DisplayName("Should allow setting properties to null after initialization")
        void shouldAllowSettingPropertiesToNullAfterInitialization() {
            // Given
            eventTicket.setId(mockEventTicketId);
            eventTicket.setTicket(mockTicket);
            eventTicket.setEvent(mockEvent);

            // When
            eventTicket.setId(null);
            eventTicket.setTicket(null);
            eventTicket.setEvent(null);

            // Then
            assertThat(eventTicket.getId()).isNull();
            assertThat(eventTicket.getTicket()).isNull();
            assertThat(eventTicket.getEvent()).isNull();
        }
    }

    @Nested
    @DisplayName("Integration with Embedded ID Tests")
    class EmbeddedIdIntegrationTests {

        @Test
        @DisplayName("Should properly integrate with EventTicketId when created via constructor")
        void shouldProperlyIntegrateWithEventTicketIdWhenCreatedViaConstructor() {
            // Given
            Long ticketId = 42L;
            Long eventId = 84L;
            when(mockTicket.getTicketId()).thenReturn(ticketId);
            when(mockEvent.getEventId()).thenReturn(eventId);

            // When
            EventTicket result = new EventTicket(mockTicket, mockEvent);

            // Then
            assertThat(result.getId()).isNotNull();
            assertThat(result.getId()).isInstanceOf(EventTicketId.class);
            assertThat(result.getId().getTicketId()).isEqualTo(ticketId);
            assertThat(result.getId().getEventId()).isEqualTo(eventId);
        }

        @Test
        @DisplayName("Should allow manual setting of EventTicketId")
        void shouldAllowManualSettingOfEventTicketId() {
            // Given
            EventTicketId customId = new EventTicketId(999L, 888L);

            // When
            eventTicket.setId(customId);

            // Then
            assertThat(eventTicket.getId()).isEqualTo(customId);
            assertThat(eventTicket.getId().getTicketId()).isEqualTo(999L);
            assertThat(eventTicket.getId().getEventId()).isEqualTo(888L);
        }
    }
}
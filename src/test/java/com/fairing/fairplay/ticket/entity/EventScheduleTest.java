package com.fairing.fairplay.ticket.entity;

import com.fairing.fairplay.event.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventSchedule Entity Tests")
class EventScheduleTest {

    private EventSchedule eventSchedule;
    private Event mockEvent;

    @BeforeEach
    void setUp() {
        eventSchedule = new EventSchedule();
        mockEvent = new Event();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create EventSchedule with no-args constructor")
        void shouldCreateEventScheduleWithNoArgsConstructor() {
            // Given & When
            EventSchedule schedule = new EventSchedule();

            // Then
            assertNotNull(schedule);
            assertNull(schedule.getScheduleId());
            assertNull(schedule.getEvent());
            assertNull(schedule.getDate());
            assertNull(schedule.getStartTime());
            assertNull(schedule.getEndTime());
            assertNull(schedule.getWeekday());
            assertNull(schedule.getCreatedAt());
            assertNotNull(schedule.getScheduleTickets());
            assertTrue(schedule.getScheduleTickets().isEmpty());
        }

        @Test
        @DisplayName("Should create EventSchedule with all-args constructor")
        void shouldCreateEventScheduleWithAllArgsConstructor() {
            // Given
            Long scheduleId = 1L;
            Event event = new Event();
            LocalDate date = LocalDate.of(2024, 1, 15);
            LocalTime startTime = LocalTime.of(10, 0);
            LocalTime endTime = LocalTime.of(12, 0);
            Integer weekday = 1;
            LocalDateTime createdAt = LocalDateTime.now();
            Set<ScheduleTicket> scheduleTickets = new HashSet<>();

            // When
            EventSchedule schedule = new EventSchedule(scheduleId, event, date, startTime, endTime, weekday, createdAt, scheduleTickets);

            // Then
            assertEquals(scheduleId, schedule.getScheduleId());
            assertEquals(event, schedule.getEvent());
            assertEquals(date, schedule.getDate());
            assertEquals(startTime, schedule.getStartTime());
            assertEquals(endTime, schedule.getEndTime());
            assertEquals(weekday, schedule.getWeekday());
            assertEquals(createdAt, schedule.getCreatedAt());
            assertEquals(scheduleTickets, schedule.getScheduleTickets());
        }
    }

    @Nested
    @DisplayName("Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Should set and get scheduleId")
        void shouldSetAndGetScheduleId() {
            // Given
            Long expectedId = 123L;

            // When
            eventSchedule.setScheduleId(expectedId);

            // Then
            assertEquals(expectedId, eventSchedule.getScheduleId());
        }

        @Test
        @DisplayName("Should set and get event")
        void shouldSetAndGetEvent() {
            // When
            eventSchedule.setEvent(mockEvent);

            // Then
            assertEquals(mockEvent, eventSchedule.getEvent());
        }

        @Test
        @DisplayName("Should set and get date")
        void shouldSetAndGetDate() {
            // Given
            LocalDate expectedDate = LocalDate.of(2024, 7, 15);

            // When
            eventSchedule.setDate(expectedDate);

            // Then
            assertEquals(expectedDate, eventSchedule.getDate());
        }

        @Test
        @DisplayName("Should set and get startTime")
        void shouldSetAndGetStartTime() {
            // Given
            LocalTime expectedStartTime = LocalTime.of(9, 30);

            // When
            eventSchedule.setStartTime(expectedStartTime);

            // Then
            assertEquals(expectedStartTime, eventSchedule.getStartTime());
        }

        @Test
        @DisplayName("Should set and get endTime")
        void shouldSetAndGetEndTime() {
            // Given
            LocalTime expectedEndTime = LocalTime.of(17, 45);

            // When
            eventSchedule.setEndTime(expectedEndTime);

            // Then
            assertEquals(expectedEndTime, eventSchedule.getEndTime());
        }

        @Test
        @DisplayName("Should set and get weekday")
        void shouldSetAndGetWeekday() {
            // Given
            Integer expectedWeekday = 5;

            // When
            eventSchedule.setWeekday(expectedWeekday);

            // Then
            assertEquals(expectedWeekday, eventSchedule.getWeekday());
        }

        @Test
        @DisplayName("Should set and get createdAt")
        void shouldSetAndGetCreatedAt() {
            // Given
            LocalDateTime expectedCreatedAt = LocalDateTime.of(2024, 1, 1, 12, 0, 0);

            // When
            eventSchedule.setCreatedAt(expectedCreatedAt);

            // Then
            assertEquals(expectedCreatedAt, eventSchedule.getCreatedAt());
        }

        @Test
        @DisplayName("Should set and get scheduleTickets")
        void shouldSetAndGetScheduleTickets() {
            // Given
            Set<ScheduleTicket> expectedTickets = new HashSet<>();
            ScheduleTicket ticket = new ScheduleTicket();
            expectedTickets.add(ticket);

            // When
            eventSchedule.setScheduleTickets(expectedTickets);

            // Then
            assertEquals(expectedTickets, eventSchedule.getScheduleTickets());
            assertEquals(1, eventSchedule.getScheduleTickets().size());
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle null values for optional fields")
        void shouldHandleNullValuesForOptionalFields() {
            // When
            eventSchedule.setScheduleId(null);
            eventSchedule.setWeekday(null);
            eventSchedule.setCreatedAt(null);

            // Then
            assertNull(eventSchedule.getScheduleId());
            assertNull(eventSchedule.getWeekday());
            assertNull(eventSchedule.getCreatedAt());
        }

        @Test
        @DisplayName("Should handle minimum time values")
        void shouldHandleMinimumTimeValues() {
            // Given
            LocalTime minTime = LocalTime.MIN;

            // When
            eventSchedule.setStartTime(minTime);
            eventSchedule.setEndTime(minTime);

            // Then
            assertEquals(minTime, eventSchedule.getStartTime());
            assertEquals(minTime, eventSchedule.getEndTime());
        }

        @Test
        @DisplayName("Should handle maximum time values")
        void shouldHandleMaximumTimeValues() {
            // Given
            LocalTime maxTime = LocalTime.MAX;

            // When
            eventSchedule.setStartTime(maxTime);
            eventSchedule.setEndTime(maxTime);

            // Then
            assertEquals(maxTime, eventSchedule.getStartTime());
            assertEquals(maxTime, eventSchedule.getEndTime());
        }

        @Test
        @DisplayName("Should handle minimum date values")
        void shouldHandleMinimumDateValues() {
            // Given
            LocalDate minDate = LocalDate.MIN;

            // When
            eventSchedule.setDate(minDate);

            // Then
            assertEquals(minDate, eventSchedule.getDate());
        }

        @Test
        @DisplayName("Should handle maximum date values")
        void shouldHandleMaximumDateValues() {
            // Given
            LocalDate maxDate = LocalDate.MAX;

            // When
            eventSchedule.setDate(maxDate);

            // Then
            assertEquals(maxDate, eventSchedule.getDate());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
        @DisplayName("Should handle valid weekday values")
        void shouldHandleValidWeekdayValues(int weekday) {
            // When
            eventSchedule.setWeekday(weekday);

            // Then
            assertEquals(weekday, eventSchedule.getWeekday());
        }

        @Test
        @DisplayName("Should handle extreme weekday values")
        void shouldHandleExtremeWeekdayValues() {
            // Given
            Integer extremeWeekday = Integer.MAX_VALUE;

            // When
            eventSchedule.setWeekday(extremeWeekday);

            // Then
            assertEquals(extremeWeekday, eventSchedule.getWeekday());
        }

        @Test
        @DisplayName("Should handle empty scheduleTickets set")
        void shouldHandleEmptyScheduleTicketsSet() {
            // Given
            Set<ScheduleTicket> emptySet = new HashSet<>();

            // When
            eventSchedule.setScheduleTickets(emptySet);

            // Then
            assertNotNull(eventSchedule.getScheduleTickets());
            assertTrue(eventSchedule.getScheduleTickets().isEmpty());
        }

        @Test
        @DisplayName("Should handle large scheduleTickets set")
        void shouldHandleLargeScheduleTicketsSet() {
            // Given
            Set<ScheduleTicket> largeSet = new HashSet<>();
            for (int i = 0; i < 1000; i++) {
                ScheduleTicket ticket = new ScheduleTicket();
                largeSet.add(ticket);
            }

            // When
            eventSchedule.setScheduleTickets(largeSet);

            // Then
            assertEquals(1000, eventSchedule.getScheduleTickets().size());
        }
    }

    @Nested
    @DisplayName("Business Logic Tests")
    class BusinessLogicTests {

        @Test
        @DisplayName("Should maintain consistent state when setting related fields")
        void shouldMaintainConsistentStateWhenSettingRelatedFields() {
            // Given
            LocalDate date = LocalDate.of(2024, 7, 15); // Monday
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(17, 0);
            Integer weekday = 1; // Monday

            // When
            eventSchedule.setDate(date);
            eventSchedule.setStartTime(startTime);
            eventSchedule.setEndTime(endTime);
            eventSchedule.setWeekday(weekday);

            // Then
            assertEquals(date, eventSchedule.getDate());
            assertEquals(startTime, eventSchedule.getStartTime());
            assertEquals(endTime, eventSchedule.getEndTime());
            assertEquals(weekday, eventSchedule.getWeekday());
        }

        @Test
        @DisplayName("Should handle same start and end time")
        void shouldHandleSameStartAndEndTime() {
            // Given
            LocalTime sameTime = LocalTime.of(12, 0);

            // When
            eventSchedule.setStartTime(sameTime);
            eventSchedule.setEndTime(sameTime);

            // Then
            assertEquals(sameTime, eventSchedule.getStartTime());
            assertEquals(sameTime, eventSchedule.getEndTime());
        }

        @Test
        @DisplayName("Should handle midnight times")
        void shouldHandleMidnightTimes() {
            // Given
            LocalTime midnight = LocalTime.MIDNIGHT;

            // When
            eventSchedule.setStartTime(midnight);
            eventSchedule.setEndTime(midnight);

            // Then
            assertEquals(midnight, eventSchedule.getStartTime());
            assertEquals(midnight, eventSchedule.getEndTime());
        }

        @Test
        @DisplayName("Should handle noon time")
        void shouldHandleNoonTime() {
            // Given
            LocalTime noon = LocalTime.NOON;

            // When
            eventSchedule.setStartTime(noon);
            eventSchedule.setEndTime(noon);

            // Then
            assertEquals(noon, eventSchedule.getStartTime());
            assertEquals(noon, eventSchedule.getEndTime());
        }
    }

    @Nested
    @DisplayName("Object State Tests")
    class ObjectStateTests {

        @Test
        @DisplayName("Should initialize with empty HashSet for scheduleTickets")
        void shouldInitializeWithEmptyHashSetForScheduleTickets() {
            // Given & When
            EventSchedule newSchedule = new EventSchedule();

            // Then
            assertNotNull(newSchedule.getScheduleTickets());
            assertTrue(newSchedule.getScheduleTickets() instanceof HashSet);
            assertTrue(newSchedule.getScheduleTickets().isEmpty());
        }

        @Test
        @DisplayName("Should maintain separate instances of scheduleTickets for different objects")
        void shouldMaintainSeparateInstancesOfScheduleTicketsForDifferentObjects() {
            // Given
            EventSchedule schedule1 = new EventSchedule();
            EventSchedule schedule2 = new EventSchedule();
            ScheduleTicket ticket = new ScheduleTicket();

            // When
            schedule1.getScheduleTickets().add(ticket);

            // Then
            assertEquals(1, schedule1.getScheduleTickets().size());
            assertEquals(0, schedule2.getScheduleTickets().size());
            assertNotSame(schedule1.getScheduleTickets(), schedule2.getScheduleTickets());
        }

        @Test
        @DisplayName("Should handle modification of scheduleTickets after setting")
        void shouldHandleModificationOfScheduleTicketsAfterSetting() {
            // Given
            Set<ScheduleTicket> tickets = new HashSet<>();
            ScheduleTicket ticket1 = new ScheduleTicket();
            tickets.add(ticket1);
            eventSchedule.setScheduleTickets(tickets);

            // When
            ScheduleTicket ticket2 = new ScheduleTicket();
            eventSchedule.getScheduleTickets().add(ticket2);

            // Then
            assertEquals(2, eventSchedule.getScheduleTickets().size());
            assertTrue(eventSchedule.getScheduleTickets().contains(ticket1));
            assertTrue(eventSchedule.getScheduleTickets().contains(ticket2));
        }
    }

    @Nested
    @DisplayName("Data Validation Tests")
    class DataValidationTests {

        @Test
        @DisplayName("Should handle very long schedule ID")
        void shouldHandleVeryLongScheduleId() {
            // Given
            Long veryLongId = Long.MAX_VALUE;

            // When
            eventSchedule.setScheduleId(veryLongId);

            // Then
            assertEquals(veryLongId, eventSchedule.getScheduleId());
        }

        @Test
        @DisplayName("Should handle negative schedule ID")
        void shouldHandleNegativeScheduleId() {
            // Given
            Long negativeId = -1L;

            // When
            eventSchedule.setScheduleId(negativeId);

            // Then
            assertEquals(negativeId, eventSchedule.getScheduleId());
        }

        @Test
        @DisplayName("Should handle zero schedule ID")
        void shouldHandleZeroScheduleId() {
            // Given
            Long zeroId = 0L;

            // When
            eventSchedule.setScheduleId(zeroId);

            // Then
            assertEquals(zeroId, eventSchedule.getScheduleId());
        }

        @Test
        @DisplayName("Should handle future dates")
        void shouldHandleFutureDates() {
            // Given
            LocalDate futureDate = LocalDate.of(2030, 12, 31);

            // When
            eventSchedule.setDate(futureDate);

            // Then
            assertEquals(futureDate, eventSchedule.getDate());
        }

        @Test
        @DisplayName("Should handle past dates")
        void shouldHandlePastDates() {
            // Given
            LocalDate pastDate = LocalDate.of(2000, 1, 1);

            // When
            eventSchedule.setDate(pastDate);

            // Then
            assertEquals(pastDate, eventSchedule.getDate());
        }

        @Test
        @DisplayName("Should handle leap year dates")
        void shouldHandleLeapYearDates() {
            // Given
            LocalDate leapYearDate = LocalDate.of(2024, 2, 29);

            // When
            eventSchedule.setDate(leapYearDate);

            // Then
            assertEquals(leapYearDate, eventSchedule.getDate());
        }
    }
}
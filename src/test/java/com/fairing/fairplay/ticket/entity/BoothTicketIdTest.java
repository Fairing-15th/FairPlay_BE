package com.fairing.fairplay.ticket.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

@DisplayName("BoothTicketId Tests")
class BoothTicketIdTest {

    private BoothTicketId boothTicketId;
    private static final Long TICKET_ID = 123L;
    private static final Long BOOTH_ID = 456L;

    @BeforeEach
    void setUp() {
        boothTicketId = new BoothTicketId(TICKET_ID, BOOTH_ID);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create instance with no-args constructor")
        void shouldCreateInstanceWithNoArgsConstructor() {
            // When
            BoothTicketId id = new BoothTicketId();
            
            // Then
            assertNotNull(id);
            assertNull(getTicketId(id));
            assertNull(getBoothId(id));
        }

        @Test
        @DisplayName("Should create instance with all-args constructor")
        void shouldCreateInstanceWithAllArgsConstructor() {
            // When
            BoothTicketId id = new BoothTicketId(TICKET_ID, BOOTH_ID);
            
            // Then
            assertNotNull(id);
            assertEquals(TICKET_ID, getTicketId(id));
            assertEquals(BOOTH_ID, getBoothId(id));
        }

        @Test
        @DisplayName("Should create instance with null values")
        void shouldCreateInstanceWithNullValues() {
            // When
            BoothTicketId id = new BoothTicketId(null, null);
            
            // Then
            assertNotNull(id);
            assertNull(getTicketId(id));
            assertNull(getBoothId(id));
        }

        @Test
        @DisplayName("Should create instance with mixed null and non-null values")
        void shouldCreateInstanceWithMixedNullValues() {
            // When
            BoothTicketId id1 = new BoothTicketId(TICKET_ID, null);
            BoothTicketId id2 = new BoothTicketId(null, BOOTH_ID);
            
            // Then
            assertNotNull(id1);
            assertEquals(TICKET_ID, getTicketId(id1));
            assertNull(getBoothId(id1));
            
            assertNotNull(id2);
            assertNull(getTicketId(id2));
            assertEquals(BOOTH_ID, getBoothId(id2));
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsAndHashCodeTests {

        @Test
        @DisplayName("Should be equal to itself")
        void shouldBeEqualToItself() {
            // Then
            assertEquals(boothTicketId, boothTicketId);
            assertEquals(boothTicketId.hashCode(), boothTicketId.hashCode());
        }

        @Test
        @DisplayName("Should be equal to another instance with same values")
        void shouldBeEqualToAnotherInstanceWithSameValues() {
            // Given
            BoothTicketId other = new BoothTicketId(TICKET_ID, BOOTH_ID);
            
            // Then
            assertEquals(boothTicketId, other);
            assertEquals(other, boothTicketId);
            assertEquals(boothTicketId.hashCode(), other.hashCode());
        }

        @Test
        @DisplayName("Should not be equal to null")
        void shouldNotBeEqualToNull() {
            // Then
            assertNotEquals(boothTicketId, null);
        }

        @Test
        @DisplayName("Should not be equal to different class")
        void shouldNotBeEqualToDifferentClass() {
            // Given
            String differentClass = "different";
            
            // Then
            assertNotEquals(boothTicketId, differentClass);
        }

        @Test
        @DisplayName("Should not be equal when ticket IDs differ")
        void shouldNotBeEqualWhenTicketIdsDiffer() {
            // Given
            BoothTicketId other = new BoothTicketId(999L, BOOTH_ID);
            
            // Then
            assertNotEquals(boothTicketId, other);
            assertNotEquals(other, boothTicketId);
        }

        @Test
        @DisplayName("Should not be equal when booth IDs differ")
        void shouldNotBeEqualWhenBoothIdsDiffer() {
            // Given
            BoothTicketId other = new BoothTicketId(TICKET_ID, 999L);
            
            // Then
            assertNotEquals(boothTicketId, other);
            assertNotEquals(other, boothTicketId);
        }

        @Test
        @DisplayName("Should be equal when both have null values")
        void shouldBeEqualWhenBothHaveNullValues() {
            // Given
            BoothTicketId id1 = new BoothTicketId(null, null);
            BoothTicketId id2 = new BoothTicketId(null, null);
            
            // Then
            assertEquals(id1, id2);
            assertEquals(id1.hashCode(), id2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when one has null ticket ID")
        void shouldNotBeEqualWhenOneHasNullTicketId() {
            // Given
            BoothTicketId withNull = new BoothTicketId(null, BOOTH_ID);
            
            // Then
            assertNotEquals(boothTicketId, withNull);
            assertNotEquals(withNull, boothTicketId);
        }

        @Test
        @DisplayName("Should not be equal when one has null booth ID")
        void shouldNotBeEqualWhenOneHasNullBoothId() {
            // Given
            BoothTicketId withNull = new BoothTicketId(TICKET_ID, null);
            
            // Then
            assertNotEquals(boothTicketId, withNull);
            assertNotEquals(withNull, boothTicketId);
        }

        @Test
        @DisplayName("Should maintain hash code consistency")
        void shouldMaintainHashCodeConsistency() {
            // Given
            int initialHashCode = boothTicketId.hashCode();
            
            // When - calling hashCode multiple times
            int secondHashCode = boothTicketId.hashCode();
            int thirdHashCode = boothTicketId.hashCode();
            
            // Then
            assertEquals(initialHashCode, secondHashCode);
            assertEquals(initialHashCode, thirdHashCode);
        }

        @Test
        @DisplayName("Should have consistent hash codes for equal objects")
        void shouldHaveConsistentHashCodesForEqualObjects() {
            // Given
            BoothTicketId id1 = new BoothTicketId(TICKET_ID, BOOTH_ID);
            BoothTicketId id2 = new BoothTicketId(TICKET_ID, BOOTH_ID);
            
            // Then
            assertEquals(id1, id2);
            assertEquals(id1.hashCode(), id2.hashCode());
        }
    }

    @Nested
    @DisplayName("Serialization Tests")
    class SerializationTests {

        @Test
        @DisplayName("Should implement Serializable interface")
        void shouldImplementSerializableInterface() {
            // Then
            assertInstanceOf(Serializable.class, boothTicketId);
        }

        @Test
        @DisplayName("Should be serializable and deserializable")
        void shouldBeSerializableAndDeserializable() throws Exception {
            // When - serialize
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(boothTicketId);
            oos.close();
            
            // When - deserialize
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            BoothTicketId deserialized = (BoothTicketId) ois.readObject();
            ois.close();
            
            // Then
            assertNotNull(deserialized);
            assertEquals(boothTicketId, deserialized);
            assertEquals(TICKET_ID, getTicketId(deserialized));
            assertEquals(BOOTH_ID, getBoothId(deserialized));
        }

        @Test
        @DisplayName("Should serialize and deserialize null values correctly")
        void shouldSerializeAndDeserializeNullValuesCorrectly() throws Exception {
            // Given
            BoothTicketId idWithNulls = new BoothTicketId(null, null);
            
            // When - serialize
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(idWithNulls);
            oos.close();
            
            // When - deserialize
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            BoothTicketId deserialized = (BoothTicketId) ois.readObject();
            ois.close();
            
            // Then
            assertNotNull(deserialized);
            assertEquals(idWithNulls, deserialized);
            assertNull(getTicketId(deserialized));
            assertNull(getBoothId(deserialized));
        }

        @Test
        @DisplayName("Should maintain equality after serialization round trip")
        void shouldMaintainEqualityAfterSerializationRoundTrip() throws Exception {
            // When - serialize and deserialize
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(boothTicketId);
            oos.close();
            
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            BoothTicketId deserialized = (BoothTicketId) ois.readObject();
            ois.close();
            
            // Then
            assertEquals(boothTicketId, deserialized);
            assertEquals(boothTicketId.hashCode(), deserialized.hashCode());
        }
    }

    @Nested
    @DisplayName("Field Tests")
    class FieldTests {

        @Test
        @DisplayName("Should have ticketId field with Column annotation")
        void shouldHaveTicketIdFieldWithColumnAnnotation() throws NoSuchFieldException {
            // When
            Field ticketIdField = BoothTicketId.class.getDeclaredField("ticketId");
            
            // Then
            assertNotNull(ticketIdField);
            assertTrue(ticketIdField.isAnnotationPresent(jakarta.persistence.Column.class));
            assertEquals("ticket_id", ticketIdField.getAnnotation(jakarta.persistence.Column.class).name());
        }

        @Test
        @DisplayName("Should have boothId field with Column annotation")
        void shouldHaveBoothIdFieldWithColumnAnnotation() throws NoSuchFieldException {
            // When
            Field boothIdField = BoothTicketId.class.getDeclaredField("boothId");
            
            // Then
            assertNotNull(boothIdField);
            assertTrue(boothIdField.isAnnotationPresent(jakarta.persistence.Column.class));
            assertEquals("booth_id", boothIdField.getAnnotation(jakarta.persistence.Column.class).name());
        }

        @Test
        @DisplayName("Should have private fields")
        void shouldHavePrivateFields() throws NoSuchFieldException {
            // When
            Field ticketIdField = BoothTicketId.class.getDeclaredField("ticketId");
            Field boothIdField = BoothTicketId.class.getDeclaredField("boothId");
            
            // Then
            assertTrue(java.lang.reflect.Modifier.isPrivate(ticketIdField.getModifiers()));
            assertTrue(java.lang.reflect.Modifier.isPrivate(boothIdField.getModifiers()));
        }
    }

    @Nested
    @DisplayName("Class Annotation Tests")
    class ClassAnnotationTests {

        @Test
        @DisplayName("Should have Embeddable annotation")
        void shouldHaveEmbeddableAnnotation() {
            // Then
            assertTrue(BoothTicketId.class.isAnnotationPresent(jakarta.persistence.Embeddable.class));
        }

        @Test
        @DisplayName("Should have NoArgsConstructor annotation")
        void shouldHaveNoArgsConstructorAnnotation() {
            // Then
            assertTrue(BoothTicketId.class.isAnnotationPresent(lombok.NoArgsConstructor.class));
        }

        @Test
        @DisplayName("Should have AllArgsConstructor annotation")
        void shouldHaveAllArgsConstructorAnnotation() {
            // Then
            assertTrue(BoothTicketId.class.isAnnotationPresent(lombok.AllArgsConstructor.class));
        }

        @Test
        @DisplayName("Should have EqualsAndHashCode annotation")
        void shouldHaveEqualsAndHashCodeAnnotation() {
            // Then
            assertTrue(BoothTicketId.class.isAnnotationPresent(lombok.EqualsAndHashCode.class));
        }
    }

    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle maximum Long values")
        void shouldHandleMaximumLongValues() {
            // Given
            BoothTicketId id = new BoothTicketId(Long.MAX_VALUE, Long.MAX_VALUE);
            
            // Then
            assertNotNull(id);
            assertEquals(Long.MAX_VALUE, getTicketId(id));
            assertEquals(Long.MAX_VALUE, getBoothId(id));
        }

        @Test
        @DisplayName("Should handle minimum Long values")
        void shouldHandleMinimumLongValues() {
            // Given
            BoothTicketId id = new BoothTicketId(Long.MIN_VALUE, Long.MIN_VALUE);
            
            // Then
            assertNotNull(id);
            assertEquals(Long.MIN_VALUE, getTicketId(id));
            assertEquals(Long.MIN_VALUE, getBoothId(id));
        }

        @Test
        @DisplayName("Should handle zero values")
        void shouldHandleZeroValues() {
            // Given
            BoothTicketId id = new BoothTicketId(0L, 0L);
            
            // Then
            assertNotNull(id);
            assertEquals(0L, getTicketId(id));
            assertEquals(0L, getBoothId(id));
        }

        @Test
        @DisplayName("Should handle negative values")
        void shouldHandleNegativeValues() {
            // Given
            BoothTicketId id = new BoothTicketId(-1L, -100L);
            
            // Then
            assertNotNull(id);
            assertEquals(-1L, getTicketId(id));
            assertEquals(-100L, getBoothId(id));
        }
    }

    @Nested
    @DisplayName("JPA Composite Key Requirements")
    class JpaCompositeKeyTests {

        @Test
        @DisplayName("Should satisfy JPA composite key requirements")
        void shouldSatisfyJpaCompositeKeyRequirements() {
            // Then - verify all JPA composite key requirements are met
            assertTrue(BoothTicketId.class.isAnnotationPresent(jakarta.persistence.Embeddable.class));
            assertTrue(Serializable.class.isAssignableFrom(BoothTicketId.class));
            
            // Verify public no-arg constructor exists (via Lombok)
            assertDoesNotThrow(() -> new BoothTicketId());
            
            // Verify equals and hashCode are properly implemented (via Lombok)
            BoothTicketId id1 = new BoothTicketId(1L, 2L);
            BoothTicketId id2 = new BoothTicketId(1L, 2L);
            assertEquals(id1, id2);
            assertEquals(id1.hashCode(), id2.hashCode());
        }

        @Test
        @DisplayName("Should work correctly as HashMap key")
        void shouldWorkCorrectlyAsHashMapKey() {
            // Given
            java.util.Map<BoothTicketId, String> map = new java.util.HashMap<>();
            BoothTicketId key1 = new BoothTicketId(1L, 2L);
            BoothTicketId key2 = new BoothTicketId(1L, 2L);
            String value = "test value";
            
            // When
            map.put(key1, value);
            
            // Then
            assertEquals(value, map.get(key2));
            assertTrue(map.containsKey(key2));
        }

        @Test
        @DisplayName("Should work correctly in HashSet")
        void shouldWorkCorrectlyInHashSet() {
            // Given
            java.util.Set<BoothTicketId> set = new java.util.HashSet<>();
            BoothTicketId id1 = new BoothTicketId(1L, 2L);
            BoothTicketId id2 = new BoothTicketId(1L, 2L);
            
            // When
            set.add(id1);
            
            // Then
            assertTrue(set.contains(id2));
            assertEquals(1, set.size());
            
            // When adding the same logical key again
            set.add(id2);
            
            // Then size should remain the same
            assertEquals(1, set.size());
        }
    }

    // Helper methods to access private fields for testing
    private Long getTicketId(BoothTicketId id) {
        try {
            Field field = BoothTicketId.class.getDeclaredField("ticketId");
            field.setAccessible(true);
            return (Long) field.get(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get ticketId", e);
        }
    }

    private Long getBoothId(BoothTicketId id) {
        try {
            Field field = BoothTicketId.class.getDeclaredField("boothId");
            field.setAccessible(true);
            return (Long) field.get(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get boothId", e);
        }
    }
}
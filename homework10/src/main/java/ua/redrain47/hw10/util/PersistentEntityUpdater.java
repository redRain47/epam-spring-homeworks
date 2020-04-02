package ua.redrain47.hw10.util;

import ua.redrain47.hw10.entity.Address;
import ua.redrain47.hw10.entity.Student;
import ua.redrain47.hw10.entity.University;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersistentEntityUpdater {
    public static void updateAddress(Address persistentAddress, Address updatedAddress) {
        persistentAddress.setCity(updatedAddress.getCity());
        persistentAddress.setHouse(updatedAddress.getHouse());
        persistentAddress.setStreet(updatedAddress.getStreet());
        persistentAddress.setRegion(updatedAddress.getRegion());
        persistentAddress.setZipcode(updatedAddress.getZipcode());
    }

    public static void updateUniversity(University persistentUniversity, University updatedUniversity) {
        persistentUniversity.setName(updatedUniversity.getName());
        updateStudents(persistentUniversity.getStudents(), updatedUniversity.getStudents());
    }

    public static void updateStudent(Student persistentStudent, Student updatedStudent, boolean updateUniversity) {
        persistentStudent.setFirstName(updatedStudent.getFirstName());
        persistentStudent.setLastName(updatedStudent.getLastName());

        Address persistedAddress = persistentStudent.getAddress();
        Address updatedAddress = updatedStudent.getAddress();

        if (updatedAddress.getId().equals(persistedAddress.getId())) {
            updateAddress(persistedAddress, updatedAddress);
        } else if (Objects.nonNull(updatedAddress.getId())) {
            persistentStudent.setAddress(updatedAddress); // TODO: remove
        } else {
            Address transientAddress = new Address();
            updateAddress(transientAddress, updatedAddress);
            persistentStudent.setAddress(transientAddress);
        }

        if (updateUniversity) {
            University persistentUniversity = persistentStudent.getUniversity();
            University updatedUniversity = updatedStudent.getUniversity();

            if (updatedUniversity.getId().equals(persistentUniversity.getId())) {
                updateUniversity(persistentUniversity, updatedUniversity);
            } else if (Objects.nonNull(updatedUniversity.getId())) {
                persistentStudent.setUniversity(updatedUniversity); // TODO: remove
            } else {
                University transientUniversity = new University();
                updateUniversity(transientUniversity, updatedUniversity);
                persistentStudent.setUniversity(transientUniversity);
            }
        }
    }

    private static void updateStudents(Set<Student> persistentStudents, Set<Student> newStudents) {
        if (persistentStudents != null && newStudents != null) {

            Map<UUID, Student> stillExistentStudents = newStudents
                    .stream()
                    .filter(j -> Objects.nonNull(j.getId()))
                    .collect(Collectors.toMap(Student::getId, Function.identity()));

            List<Student> studentsToAdd = newStudents
                    .stream()
                    .filter(j -> Objects.isNull(j.getId()))
                    .collect(Collectors.toList());

            Iterator<Student> persistentIterator = persistentStudents.iterator();

            while (persistentIterator.hasNext()) {
                Student persistentStudent = persistentIterator.next();

                if (stillExistentStudents.containsKey(persistentStudent.getId())) {
                    Student updatedStudent = stillExistentStudents.get(persistentStudent.getId());
                    updateStudent(persistentStudent, updatedStudent, false);
                } else {
                    persistentIterator.remove();
                    persistentStudent.setUniversity(null);
                }
            }

            persistentStudents.addAll(studentsToAdd);
        }
    }
}

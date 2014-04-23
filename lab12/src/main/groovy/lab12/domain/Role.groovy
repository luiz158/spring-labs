package lab12.domain

import groovy.transform.TypeChecked

@TypeChecked
enum Role {
    USER,
    ADMIN,
    POWER_USER

    @Override
    String toString() {
        return name()
    }
}

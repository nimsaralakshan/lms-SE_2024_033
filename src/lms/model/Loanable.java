package lms.model;

import lms.exception.ItemNotAvailableException;

/** Contract for anything that can be loaned. Week 5: failures are now exceptions. */
public interface Loanable {
    void loan(Member member) throws ItemNotAvailableException;
    void returnItem() throws ItemNotAvailableException;
}
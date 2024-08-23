package com.techlab.bankdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Loan;
import com.techlab.bankdb.entity.LoanStatus;
import com.techlab.bankdb.exceptions.LoanNotFoundException;
import com.techlab.bankdb.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    private PageResponse<Loan> loanPageResponse = new PageResponse<>();

    @Override
    public PageResponse<Loan> getAllLoans(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Loan> loanPage = loanRepository.findAll(page);
        loanPageResponse.setContent(loanPage.getContent());
        loanPageResponse.setSize(loanPage.getSize());
        loanPageResponse.setTotalElements(loanPage.getTotalElements());
        loanPageResponse.setTotalPages(loanPage.getTotalPages());
        loanPageResponse.setLastPage(loanPage.isLast());

        return loanPageResponse;
    }

    @Override
    public Loan getLoanById(int loanId) {
        Optional<Loan> dbLoan = loanRepository.findById(loanId);
        if (!dbLoan.isPresent()) {
            throw new LoanNotFoundException();
        }
        return dbLoan.get();
    }

    @Override
    public PageResponse<Loan> getLoanByStatus(LoanStatus status, int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Loan> loanPage = loanRepository.findByLoanStatus(status, page);
        loanPageResponse.setContent(loanPage.getContent());
        loanPageResponse.setSize(loanPage.getSize());
        loanPageResponse.setTotalElements(loanPage.getTotalElements());
        loanPageResponse.setTotalPages(loanPage.getTotalPages());
        loanPageResponse.setLastPage(loanPage.isLast());

        return loanPageResponse;
    }

    @Override
    public Loan addUpdateLoan(Loan loan) {
        if (loan.getLoanId() != 0) {
            Optional<Loan> dbLoan = loanRepository.findById(loan.getLoanId());
            if (!dbLoan.isPresent()) {
                throw new LoanNotFoundException();
            }
        }
        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoanById(int loanId) {
        Optional<Loan> dbLoan = loanRepository.findById(loanId);
        if (!dbLoan.isPresent()) {
            throw new LoanNotFoundException();
        }
        loanRepository.deleteById(loanId);
    }
}

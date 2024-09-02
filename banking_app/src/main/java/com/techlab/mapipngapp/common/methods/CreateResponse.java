package com.techlab.mapipngapp.common.methods;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.dto.PageResponse;



import lombok.NoArgsConstructor;



@NoArgsConstructor
@Component
public class CreateResponse<T> {
	
	
	public PageResponse<T> createPageResponse(Page<T> paymentPage) {
        PageResponse<T> paymentPageResponse = new PageResponse<>();
        paymentPageResponse.setContent(paymentPage.getContent());
        paymentPageResponse.setSize(paymentPage.getSize());
        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
        paymentPageResponse.setLastPage(paymentPage.isLast());

        return paymentPageResponse;
    }

}

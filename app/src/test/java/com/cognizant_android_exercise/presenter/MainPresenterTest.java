package com.cognizant_android_exercise.presenter;

import com.cognizant_android_exercise.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainPresenterTest {
    @Mock
    private MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new MainPresenter(view);
    }

    @Test
    public void shouldShowNoInternetToastMessageWhenInternetIsNotAvailableAndRefreshButtonIsClicked() {
        boolean internetConnectivity = false;

        presenter.onRefreshButtonClick(internetConnectivity);

        verify(view).showNoInternetToastMessage();
    }
}
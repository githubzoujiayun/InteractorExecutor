package com.tonilopezmr.sample.domain.interactor;

import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;
import com.tonilopezmr.sample.domain.Subject;
import com.tonilopezmr.sample.domain.exception.SubjectException;
import com.tonilopezmr.sample.domain.repository.SubjectRepository;

/**
 * @author toni.
 */
public class CreateSubjectUseCaseImp extends AbstractSubjectUseCase implements SubjectUseCase {

    private Callback callback;
    private Subject subject;

    public CreateSubjectUseCaseImp(Executor executor, MainThread mainThread, SubjectRepository subjectRepository) {
        super(executor, mainThread, subjectRepository);
    }

    //Run interactor
    @Override
    public void execute(Subject subject, final Callback callback) {
        if (callback == null){
            throw new IllegalArgumentException("Callback parameter can't be null");
        }

        this.callback = callback;
        this.subject = subject;
        getExecutor().run(this);
    }


    //Interactor Use case
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getSubjectRepository().createSubject(subject, new SubjectRepository.SubjectUseCase() {
            @Override
            public void onMissionAccomplished(final Subject subject) {
                getMainThread().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onMissionAccomplished(subject);
                    }
                });
            }

            @Override
            public void onError(final SubjectException subjectException) {
                getMainThread().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(subjectException);
                    }
                });
            }
        });
    }
}

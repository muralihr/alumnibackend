import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AlumniDetailComponent } from '../../../../../../main/webapp/app/entities/alumni/alumni-detail.component';
import { AlumniService } from '../../../../../../main/webapp/app/entities/alumni/alumni.service';
import { Alumni } from '../../../../../../main/webapp/app/entities/alumni/alumni.model';

describe('Component Tests', () => {

    describe('Alumni Management Detail Component', () => {
        let comp: AlumniDetailComponent;
        let fixture: ComponentFixture<AlumniDetailComponent>;
        let service: AlumniService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [AlumniDetailComponent],
                providers: [
                    MockBackend,
                    BaseRequestOptions,
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    {
                        provide: Http,
                        useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                            return new Http(backendInstance, defaultOptions);
                        },
                        deps: [MockBackend, BaseRequestOptions]
                    },
                    AlumniService
                ]
            }).overrideComponent(AlumniDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlumniDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlumniService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Alumni(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.alumni).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});

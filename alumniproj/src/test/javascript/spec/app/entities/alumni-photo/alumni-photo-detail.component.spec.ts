import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AlumniPhotoDetailComponent } from '../../../../../../main/webapp/app/entities/alumni-photo/alumni-photo-detail.component';
import { AlumniPhotoService } from '../../../../../../main/webapp/app/entities/alumni-photo/alumni-photo.service';
import { AlumniPhoto } from '../../../../../../main/webapp/app/entities/alumni-photo/alumni-photo.model';

describe('Component Tests', () => {

    describe('AlumniPhoto Management Detail Component', () => {
        let comp: AlumniPhotoDetailComponent;
        let fixture: ComponentFixture<AlumniPhotoDetailComponent>;
        let service: AlumniPhotoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [AlumniPhotoDetailComponent],
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
                    AlumniPhotoService
                ]
            }).overrideComponent(AlumniPhotoDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlumniPhotoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlumniPhotoService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AlumniPhoto(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.alumniPhoto).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});

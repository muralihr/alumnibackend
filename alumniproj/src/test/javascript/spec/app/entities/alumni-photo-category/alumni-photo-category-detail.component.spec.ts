import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AlumniPhotoCategoryDetailComponent } from '../../../../../../main/webapp/app/entities/alumni-photo-category/alumni-photo-category-detail.component';
import { AlumniPhotoCategoryService } from '../../../../../../main/webapp/app/entities/alumni-photo-category/alumni-photo-category.service';
import { AlumniPhotoCategory } from '../../../../../../main/webapp/app/entities/alumni-photo-category/alumni-photo-category.model';

describe('Component Tests', () => {

    describe('AlumniPhotoCategory Management Detail Component', () => {
        let comp: AlumniPhotoCategoryDetailComponent;
        let fixture: ComponentFixture<AlumniPhotoCategoryDetailComponent>;
        let service: AlumniPhotoCategoryService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [AlumniPhotoCategoryDetailComponent],
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
                    AlumniPhotoCategoryService
                ]
            }).overrideComponent(AlumniPhotoCategoryDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AlumniPhotoCategoryDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlumniPhotoCategoryService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AlumniPhotoCategory(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.alumniPhotoCategory).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { AlumniPhotoCategory } from './alumni-photo-category.model';
import { AlumniPhotoCategoryPopupService } from './alumni-photo-category-popup.service';
import { AlumniPhotoCategoryService } from './alumni-photo-category.service';
@Component({
    selector: 'jhi-alumni-photo-category-dialog',
    templateUrl: './alumni-photo-category-dialog.component.html'
})
export class AlumniPhotoCategoryDialogComponent implements OnInit {

    alumniPhotoCategory: AlumniPhotoCategory;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private alumniPhotoCategoryService: AlumniPhotoCategoryService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.alumniPhotoCategory.id !== undefined) {
            this.alumniPhotoCategoryService.update(this.alumniPhotoCategory)
                .subscribe((res: AlumniPhotoCategory) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.alumniPhotoCategoryService.create(this.alumniPhotoCategory)
                .subscribe((res: AlumniPhotoCategory) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: AlumniPhotoCategory) {
        this.eventManager.broadcast({ name: 'alumniPhotoCategoryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-alumni-photo-category-popup',
    template: ''
})
export class AlumniPhotoCategoryPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPhotoCategoryPopupService: AlumniPhotoCategoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.alumniPhotoCategoryPopupService
                    .open(AlumniPhotoCategoryDialogComponent, params['id']);
            } else {
                this.modalRef = this.alumniPhotoCategoryPopupService
                    .open(AlumniPhotoCategoryDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

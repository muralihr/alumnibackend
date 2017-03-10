import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, DataUtils } from 'ng-jhipster';

import { AlumniPhoto } from './alumni-photo.model';
import { AlumniPhotoPopupService } from './alumni-photo-popup.service';
import { AlumniPhotoService } from './alumni-photo.service';
import { User, UserService } from '../../shared';
@Component({
    selector: 'jhi-alumni-photo-dialog',
    templateUrl: './alumni-photo-dialog.component.html'
})
export class AlumniPhotoDialogComponent implements OnInit {

    alumniPhoto: AlumniPhoto;
    authorities: any[];
    isSaving: boolean;

    users: User[];
    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: DataUtils,
        private alertService: AlertService,
        private alumniPhotoService: AlumniPhotoService,
        private userService: UserService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.query().subscribe(
            (res: Response) => { this.users = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData($event, alumniPhoto, field, isImage) {
        if ($event.target.files && $event.target.files[0]) {
            let $file = $event.target.files[0];
            if (isImage && !/^image\//.test($file.type)) {
                return;
            }
            this.dataUtils.toBase64($file, (base64Data) => {
                alumniPhoto[field] = base64Data;
                alumniPhoto[`${field}ContentType`] = $file.type;
            });
        }
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.alumniPhoto.id !== undefined) {
            this.alumniPhotoService.update(this.alumniPhoto)
                .subscribe((res: AlumniPhoto) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.alumniPhotoService.create(this.alumniPhoto)
                .subscribe((res: AlumniPhoto) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: AlumniPhoto) {
        this.eventManager.broadcast({ name: 'alumniPhotoListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-alumni-photo-popup',
    template: ''
})
export class AlumniPhotoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPhotoPopupService: AlumniPhotoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.alumniPhotoPopupService
                    .open(AlumniPhotoDialogComponent, params['id']);
            } else {
                this.modalRef = this.alumniPhotoPopupService
                    .open(AlumniPhotoDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Alumni } from './alumni.model';
import { AlumniPopupService } from './alumni-popup.service';
import { AlumniService } from './alumni.service';
import { User, UserService } from '../../shared';
@Component({
    selector: 'jhi-alumni-dialog',
    templateUrl: './alumni-dialog.component.html'
})
export class AlumniDialogComponent implements OnInit {

    alumni: Alumni;
    authorities: any[];
    isSaving: boolean;

    users: User[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private alumniService: AlumniService,
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
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.alumni.id !== undefined) {
            this.alumniService.update(this.alumni)
                .subscribe((res: Alumni) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.alumniService.create(this.alumni)
                .subscribe((res: Alumni) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Alumni) {
        this.eventManager.broadcast({ name: 'alumniListModification', content: 'OK'});
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
    selector: 'jhi-alumni-popup',
    template: ''
})
export class AlumniPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPopupService: AlumniPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.alumniPopupService
                    .open(AlumniDialogComponent, params['id']);
            } else {
                this.modalRef = this.alumniPopupService
                    .open(AlumniDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

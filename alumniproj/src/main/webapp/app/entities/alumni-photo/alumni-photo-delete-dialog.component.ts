import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { AlumniPhoto } from './alumni-photo.model';
import { AlumniPhotoPopupService } from './alumni-photo-popup.service';
import { AlumniPhotoService } from './alumni-photo.service';

@Component({
    selector: 'jhi-alumni-photo-delete-dialog',
    templateUrl: './alumni-photo-delete-dialog.component.html'
})
export class AlumniPhotoDeleteDialogComponent {

    alumniPhoto: AlumniPhoto;

    constructor(
        private alumniPhotoService: AlumniPhotoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.alumniPhotoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'alumniPhotoListModification',
                content: 'Deleted an alumniPhoto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-alumni-photo-delete-popup',
    template: ''
})
export class AlumniPhotoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPhotoPopupService: AlumniPhotoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.alumniPhotoPopupService
                .open(AlumniPhotoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

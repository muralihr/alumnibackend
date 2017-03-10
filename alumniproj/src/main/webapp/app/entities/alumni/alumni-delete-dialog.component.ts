import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Alumni } from './alumni.model';
import { AlumniPopupService } from './alumni-popup.service';
import { AlumniService } from './alumni.service';

@Component({
    selector: 'jhi-alumni-delete-dialog',
    templateUrl: './alumni-delete-dialog.component.html'
})
export class AlumniDeleteDialogComponent {

    alumni: Alumni;

    constructor(
        private alumniService: AlumniService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.alumniService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'alumniListModification',
                content: 'Deleted an alumni'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-alumni-delete-popup',
    template: ''
})
export class AlumniDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPopupService: AlumniPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.alumniPopupService
                .open(AlumniDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

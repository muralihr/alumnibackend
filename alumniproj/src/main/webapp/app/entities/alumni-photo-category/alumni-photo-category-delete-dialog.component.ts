import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { AlumniPhotoCategory } from './alumni-photo-category.model';
import { AlumniPhotoCategoryPopupService } from './alumni-photo-category-popup.service';
import { AlumniPhotoCategoryService } from './alumni-photo-category.service';

@Component({
    selector: 'jhi-alumni-photo-category-delete-dialog',
    templateUrl: './alumni-photo-category-delete-dialog.component.html'
})
export class AlumniPhotoCategoryDeleteDialogComponent {

    alumniPhotoCategory: AlumniPhotoCategory;

    constructor(
        private alumniPhotoCategoryService: AlumniPhotoCategoryService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.alumniPhotoCategoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'alumniPhotoCategoryListModification',
                content: 'Deleted an alumniPhotoCategory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-alumni-photo-category-delete-popup',
    template: ''
})
export class AlumniPhotoCategoryDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private alumniPhotoCategoryPopupService: AlumniPhotoCategoryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.alumniPhotoCategoryPopupService
                .open(AlumniPhotoCategoryDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

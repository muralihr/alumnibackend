import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlumniPhotoCategory } from './alumni-photo-category.model';
import { AlumniPhotoCategoryService } from './alumni-photo-category.service';
@Injectable()
export class AlumniPhotoCategoryPopupService {
    private isOpen = false;
    constructor (
        private modalService: NgbModal,
        private router: Router,
        private alumniPhotoCategoryService: AlumniPhotoCategoryService

    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.alumniPhotoCategoryService.find(id).subscribe(alumniPhotoCategory => {
                this.alumniPhotoCategoryModalRef(component, alumniPhotoCategory);
            });
        } else {
            return this.alumniPhotoCategoryModalRef(component, new AlumniPhotoCategory());
        }
    }

    alumniPhotoCategoryModalRef(component: Component, alumniPhotoCategory: AlumniPhotoCategory): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.alumniPhotoCategory = alumniPhotoCategory;
        modalRef.result.then(result => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}

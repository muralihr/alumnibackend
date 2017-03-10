import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { AlumniPhoto } from './alumni-photo.model';
import { AlumniPhotoService } from './alumni-photo.service';
@Injectable()
export class AlumniPhotoPopupService {
    private isOpen = false;
    constructor (
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private alumniPhotoService: AlumniPhotoService

    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.alumniPhotoService.find(id).subscribe(alumniPhoto => {
                alumniPhoto.uploadTime = this.datePipe.transform(alumniPhoto.uploadTime, 'yyyy-MM-ddThh:mm');
                this.alumniPhotoModalRef(component, alumniPhoto);
            });
        } else {
            return this.alumniPhotoModalRef(component, new AlumniPhoto());
        }
    }

    alumniPhotoModalRef(component: Component, alumniPhoto: AlumniPhoto): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.alumniPhoto = alumniPhoto;
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

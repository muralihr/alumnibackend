import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataUtils } from 'ng-jhipster';
import { AlumniPhoto } from './alumni-photo.model';
import { AlumniPhotoService } from './alumni-photo.service';

@Component({
    selector: 'jhi-alumni-photo-detail',
    templateUrl: './alumni-photo-detail.component.html'
})
export class AlumniPhotoDetailComponent implements OnInit, OnDestroy {

    alumniPhoto: AlumniPhoto;
    private subscription: any;

    constructor(
        private dataUtils: DataUtils,
        private alumniPhotoService: AlumniPhotoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.alumniPhotoService.find(id).subscribe(alumniPhoto => {
            this.alumniPhoto = alumniPhoto;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}

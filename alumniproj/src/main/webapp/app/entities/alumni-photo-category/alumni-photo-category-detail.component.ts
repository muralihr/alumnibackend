import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlumniPhotoCategory } from './alumni-photo-category.model';
import { AlumniPhotoCategoryService } from './alumni-photo-category.service';

@Component({
    selector: 'jhi-alumni-photo-category-detail',
    templateUrl: './alumni-photo-category-detail.component.html'
})
export class AlumniPhotoCategoryDetailComponent implements OnInit, OnDestroy {

    alumniPhotoCategory: AlumniPhotoCategory;
    private subscription: any;

    constructor(
        private alumniPhotoCategoryService: AlumniPhotoCategoryService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.alumniPhotoCategoryService.find(id).subscribe(alumniPhotoCategory => {
            this.alumniPhotoCategory = alumniPhotoCategory;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}

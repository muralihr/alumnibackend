import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Alumni } from './alumni.model';
import { AlumniService } from './alumni.service';

@Component({
    selector: 'jhi-alumni-detail',
    templateUrl: './alumni-detail.component.html'
})
export class AlumniDetailComponent implements OnInit, OnDestroy {

    alumni: Alumni;
    private subscription: any;

    constructor(
        private alumniService: AlumniService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.alumniService.find(id).subscribe(alumni => {
            this.alumni = alumni;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}

import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { AlumniPhoto } from './alumni-photo.model';
import { DateUtils } from 'ng-jhipster';
@Injectable()
export class AlumniPhotoService {

    private resourceUrl = 'api/alumni-photos';
    private resourceSearchUrl = 'api/_search/alumni-photos';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(alumniPhoto: AlumniPhoto): Observable<AlumniPhoto> {
        let copy: AlumniPhoto = Object.assign({}, alumniPhoto);
        copy.uploadTime = this.dateUtils.toDate(alumniPhoto.uploadTime);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(alumniPhoto: AlumniPhoto): Observable<AlumniPhoto> {
        let copy: AlumniPhoto = Object.assign({}, alumniPhoto);

        copy.uploadTime = this.dateUtils.toDate(alumniPhoto.uploadTime);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AlumniPhoto> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            let jsonResponse = res.json();
            jsonResponse.uploadTime = this.dateUtils
                .convertDateTimeFromServer(jsonResponse.uploadTime);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<Response> {
        let options = this.createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<Response> {
        let options = this.createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }

    private convertResponse(res: any): any {
        let jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].uploadTime = this.dateUtils
                .convertDateTimeFromServer(jsonResponse[i].uploadTime);
        }
        res._body = jsonResponse;
        return res;
    }

    private createRequestOption(req?: any): BaseRequestOptions {
        let options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            let params: URLSearchParams = new URLSearchParams();
            params.set('page', req.page);
            params.set('size', req.size);
            if (req.sort) {
                params.paramsMap.set('sort', req.sort);
            }
            params.set('query', req.query);

            options.search = params;
        }
        return options;
    }
}

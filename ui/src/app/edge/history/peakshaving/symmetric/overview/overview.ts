import { Component } from '@angular/core';
import { AbstractHistoryChartOverview } from 'src/app/shared/genericComponents/chart/abstractHistoryChartOverview';

@Component({
    templateUrl: './overview.html'
})
export class OverviewComponent extends AbstractHistoryChartOverview {

    public override ngOnInit(): void {
        this.componentId = this.route.snapshot.params.componentId;
        super.ngOnInit();
    }
}  
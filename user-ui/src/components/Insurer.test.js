import Insurer from './Insurer';
import {mount} from "enzyme";
import React from "react";
import '../setupTests';

describe('Insurer', () => {
    let insurer;
    let component;

    beforeEach(() => {
        insurer = {
            name: "some-name",
            premium: "some-premium"
        }
        component = mount(<Insurer insurer={insurer}/>);
    })

    it('should render correctly', () => {
    });
});

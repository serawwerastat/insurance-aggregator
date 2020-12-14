import AlertBox from './AlertBox';
import {mount} from "enzyme";
import React from "react";
import '../setupTests';

describe('AlertBox', () => {
    let component;

    beforeEach(() => {
        component = mount(<AlertBox />);
    })

    it('should have given message',  () => {
        component.setProps({msg: "test"});
        expect(component.text()).toEqual("test");
    });
});

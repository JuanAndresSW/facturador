package dev.facturador.pointofsale.domain.subdomain;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import lombok.Getter;

@Getter
public class ControlOfPosUpdateCommand extends Command {
    private final RequiredPosControlData data;


    public ControlOfPosUpdateCommand(RequiredPosControlData data) {
        this.data = data;
    }

    public static class Builder {
        private RequiredPosControlData data;

        public static ControlOfPosUpdateCommand.Builder getInstance() {
            return new ControlOfPosUpdateCommand.Builder();
        }

        public ControlOfPosUpdateCommand.Builder data(RequiredPosControlData data) {
            this.data = data;
            return this;
        }

        public ControlOfPosUpdateCommand build() {
            return new ControlOfPosUpdateCommand(data);
        }
    }
}
